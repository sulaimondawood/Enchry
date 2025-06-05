package com.dawood.enchry.service.device;

import com.dawood.enchry.dto.device.DeviceRequestDTO;
import com.dawood.enchry.dto.device.DeviceResponseDTO;
import com.dawood.enchry.dto.device.DeviceResponseDTO;
import com.dawood.enchry.exception.NoDeviceFoundException;
import com.dawood.enchry.mapper.DeviceMapper;
import com.dawood.enchry.model.Device;
import com.dawood.enchry.model.User;
import com.dawood.enchry.repository.DeviceRepository;
import com.dawood.enchry.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private static final Logger log = LoggerFactory.getLogger(DeviceService.class);
    private final DeviceRepository deviceRepository;
    private final UserService userService;

    public DeviceResponseDTO addDevice(DeviceRequestDTO req, String jwt){
        Device device = new Device();

        User user = userService.extractUserFromToken(jwt);

        device.setDeviceName(req.getDeviceName());
        device.setSensorType(req.getSensorType());
        device.setDeviceDescription(req.getDeviceDescription());
        device.setUser(user);

        Device savedDevice = deviceRepository.save(device);
        log.info(savedDevice.getDeviceName());
        return DeviceMapper.toDTO(savedDevice);
    }

    public List<DeviceResponseDTO> getAllDevices(){
        List<Device> devices = deviceRepository.findAll();
       return devices.stream().map(device->DeviceMapper.toDTO(device)).toList();
    }

    public DeviceResponseDTO getDevice(UUID deviceId, String jwt){
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(()-> new NoDeviceFoundException("Device does not exist"));

        return DeviceMapper.toDTO(device);
    }


    public List<DeviceResponseDTO> getUserDevices(String jwt){

        User user = userService.extractUserFromToken(jwt);
        List<Device> devices = deviceRepository.findByUser(user);

        log.info(devices.stream().toString());
        return devices.stream()
                .map(DeviceMapper::toDTO)
                .toList();
    }

    public DeviceResponseDTO getUserDevice(UUID deviceId, String jwt){
        User user = userService.extractUserFromToken(jwt);
        Device device = deviceRepository.findByIdAndUser(deviceId,user)
                .orElseThrow(()-> new NoDeviceFoundException("Device does not exist"));

        return DeviceMapper.toDTO(device);
    }

    public void deleteDevice(String jwt, UUID deviceId){
        User user = userService.extractUserFromToken(jwt);

        Device device = deviceRepository.findByIdAndUser(deviceId, user)
                .orElseThrow(()->new NoDeviceFoundException("Device does not exist"));
        deviceRepository.delete(device);
    }

    public DeviceResponseDTO changeDeviceStatus(UUID deviceId, boolean status, String jwt){
        User user = userService.extractUserFromToken(jwt);
        Device device = deviceRepository.findByIdAndUser(deviceId,user)
                .orElseThrow(()-> new NoDeviceFoundException("Device does not exist"));

        device.setActive(status);
        deviceRepository.save(device);

        return DeviceMapper.toDTO(device);
    }
}
