package com.dawood.enchry.service;

import com.dawood.enchry.dto.climate.ClimateRequestDTO;
import com.dawood.enchry.dto.climate.ClimateResponseDTO;
import com.dawood.enchry.exception.NoDeviceFoundException;
import com.dawood.enchry.exception.UserNotFoundException;
import com.dawood.enchry.model.Device;
import com.dawood.enchry.model.User;
import com.dawood.enchry.repository.DeviceRepository;
import com.dawood.enchry.repository.UserRepository;
import com.dawood.enchry.service.device.DeviceService;
import com.goterl.lazysodium.LazySodiumJava;
import com.goterl.lazysodium.SodiumJava;
import com.goterl.lazysodium.exceptions.SodiumException;
import com.goterl.lazysodium.utils.Key;
import com.goterl.lazysodium.utils.SessionPair;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SensorCryptoService {

    private static final Logger log = LoggerFactory.getLogger(SensorCryptoService.class);
    private final Environment env;
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;
    private LazySodiumJava sodiumJava;

    @PostConstruct
    public void init(){
        sodiumJava = new LazySodiumJava(new SodiumJava());
    }

    public Device getDevice(UUID deviceId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByEmail(username).orElseThrow(()->new UserNotFoundException("User not found"));
        return deviceRepository.findByIdAndUser(deviceId, user).orElseThrow(()-> new NoDeviceFoundException("No device found"));
    }

    public void decryptDataAndSave(ClimateRequestDTO req){
        try {

            Device device = getDevice(req.getDeviceId());

            log.info(device.getDevicePublicId());

            byte[] nonce = Base64.getDecoder().decode(req.getNonce());
            byte[] sensoredData = Base64.getDecoder().decode(req.getSensoredData());
            byte[] devicePublicKeyBytes = Base64.getDecoder().decode(req.getDeviceId().toString());
            byte[] serverPublicKeyBytes = Base64.getDecoder().decode(env.getProperty("ecdh.public-key"));
            byte[] serverSecretKeyBytes = Base64.getDecoder().decode(env.getProperty("ecdh.secret-key"));


            Key serverPublicKey = Key.fromBytes(serverPublicKeyBytes);
            Key serverSecretKey = Key.fromBytes(serverSecretKeyBytes);
            Key devicePublicKey = Key.fromBytes(devicePublicKeyBytes);

            SessionPair sessionKeys = sodiumJava.cryptoKxServerSessionKeys(
                    serverPublicKey,
                    serverSecretKey,
                    devicePublicKey
            );

            byte[] sharedKeyBytes = sessionKeys.getTx();
            Key sharedKey = Key.fromBytes(sharedKeyBytes);

            // Generate from HKD
           Key derivedKey = sodiumJava.cryptoKdfDeriveFromKey(  32, // 256-bit key
                    1, // Key ID
                    "climate-sensor",
                    sharedKey);


            // Decrypt data
            byte[] decryptedData = new byte[sensoredData.length];
            boolean decryptionSuccess = sodiumJava.cryptoAeadChaCha20Poly1305Decrypt(
                    decryptedData,      // Output buffer
                    null,
                    null,
                    sensoredData,      // Ciphertext
                    sensoredData.length,
                    null,// No additional data
                    0,
                    nonce,             // Nonce
                    derivedKey.getAsBytes()         // Derived key
            );

            if (!decryptionSuccess) {
                throw new SodiumException("Decryption failed");
            }

            // Parse decrypted data (assuming it's a JSON string)
            String decryptedString = new String(decryptedData);

            log.info(decryptedString);

            // Save decrypted data to device
//            device.setLastReading(decryptedString); // Adjust based on your model
//            deviceRepository.save(device);



        }catch (SodiumException e) {
            log.error("Cryptographic error: {}", e.getMessage());
            throw new RuntimeException("Failed to decrypt data", e);

        }catch (IllegalArgumentException e) {
            log.error("Unexpected error: {}", e.getMessage());
            throw new RuntimeException("Error processing sensor data", e);
        }

        catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

}
