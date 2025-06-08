package com.dawood.enchry.service.climate;

import com.dawood.enchry.dto.climate.ClimateRequestDTO;
import com.dawood.enchry.dto.climate.ClimateResponseDTO;
import com.dawood.enchry.exception.NoDeviceFoundException;
import com.dawood.enchry.mapper.ClimateMapper;
import com.dawood.enchry.model.Climate;
import com.dawood.enchry.model.Device;
import com.dawood.enchry.model.User;
import com.dawood.enchry.repository.ClimateRepository;
import com.dawood.enchry.repository.DeviceRepository;
import com.dawood.enchry.service.UserService;
import com.dawood.enchry.service.device.DeviceService;
import com.muquit.libsodiumjna.SodiumLibrary;
import com.sun.jna.Platform;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class ClimateService {

    private static final Logger log = LoggerFactory.getLogger(ClimateService.class);
    private final DeviceRepository deviceRepository;
    private final ClimateRepository climateRepository;
    private final UserService userService;
    private final Environment env;


    @PostConstruct
    public void init(){
        try {
            String libraryPath = null;

            if(Platform.isMac()){
                libraryPath = "/usr/local/lib/libsodium.dylib";
                libraryPath = libraryPath;
                log.info("Library path in Mac: " + libraryPath);

            } else if (Platform.isWindows()) {
                libraryPath = "C:/Windows/system32/libsodium.dll";
                log.info("Library path in Windows: " + libraryPath);

            }else
            {
                // Linux
                libraryPath = "/usr/local/lib/libsodium.so";
                log.info("Library path: " + libraryPath);
            }
            log.info("loading libsodium...");
            SodiumLibrary.setLibraryPath(libraryPath);
                // To check the native library is actually loaded, print the version of
                // native sodium library
            String v = SodiumLibrary.libsodiumVersionString();
            log.info("libsodium version: " + v);
    }catch (Exception e){
            log.error("Failed to load libsodium: " + e.getMessage());
            throw new RuntimeException("Could not load libsodium library", e);
        }
    }

    public ClimateResponseDTO create(ClimateRequestDTO req){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        log.info(username);

        User user = userService.findUserByEmail(username);
        Device device = deviceRepository.findByIdAndUser(req.getDeviceId(), user)
                .orElseThrow(()->new NoDeviceFoundException("Device does not exists"));

        Climate newClimate = new Climate();

        newClimate.setNonce(req.getNonce());
        newClimate.setSalt(req.getSalt());
        newClimate.setTime(req.getTime());
        newClimate.setDevice(device);
        newClimate.setLatitude(req.getLatitude());
        newClimate.setLongitude(req.getLongitude());
        newClimate.setTimezone(req.getTimezone());
        newClimate.setSensoredData(req.getSensoredData());

        Climate savedClimate = climateRepository.save(newClimate);

        return ClimateMapper.toDTO(savedClimate);
    }

    public ClimateResponseDTO latestClimateReading(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Climate climate = climateRepository.findTopByDeviceUserEmailOrderByTimeDesc(username)
                .orElseThrow(()->new NoDeviceFoundException("Device not found or unauthorized"));

        return ClimateMapper.toDTO(climate);

    }

    public ClimateResponseDTO decryptDataAndSave(ClimateRequestDTO req){
        try {
            byte[] nonce = Base64.getDecoder().decode(req.getNonce());
            byte[] sensoredData = Base64.getDecoder().decode(req.getSensoredData());
            byte[] publicKey = Base64.getDecoder().decode(env.getProperty("ecdh.public-key"));
            byte[] secretKey = Base64.getDecoder().decode(env.getProperty("ecdh.secret-key"));

            byte[] randomBytes = SodiumLibrary.randomBytes(16);

            return null;
        }catch (Exception e){

        }
        return  null;
    }
}
