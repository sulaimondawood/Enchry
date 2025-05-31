package com.dawood.enchry.service.device;

import com.dawood.enchry.dto.device.DeviceRequestResponseDTO;
import com.dawood.enchry.mapper.DeviceMapper;
import com.dawood.enchry.model.Device;
import com.dawood.enchry.model.User;
import com.dawood.enchry.repository.DeviceRepository;
import com.dawood.enchry.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private static final Logger log = LoggerFactory.getLogger(DeviceService.class);
    private final DeviceRepository deviceRepository;
    private final UserService userService;

    public DeviceRequestResponseDTO addDevice(DeviceRequestResponseDTO req, String jwt){
        Device device = new Device();

        User user = userService.extractUserFromToken(jwt);

        device.setDeviceName(req.getDeviceName());
        device.setSensorType(req.getSensorType());
        device.setUser(user);

        Device savedDevice = deviceRepository.save(device);
        log.info(savedDevice.getDeviceName());
        return DeviceMapper.toDTO(savedDevice);
    }

}
