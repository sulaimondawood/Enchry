package com.dawood.enchry.service.device;

import com.dawood.enchry.dto.device.DeviceRequestResponseDTO;
import com.dawood.enchry.mapper.DeviceMapper;
import com.dawood.enchry.model.Device;
import com.dawood.enchry.model.User;
import com.dawood.enchry.repository.DeviceRepository;
import com.dawood.enchry.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final UserService userService;

    public DeviceRequestResponseDTO addDevice(DeviceRequestResponseDTO req, String jwt){
        Device device = new Device();

        User user = userService.extractUserFromToken(jwt);

        device.setDeviceName(req.getDeviceName());
        device.setSensorType(req.getSensorType());
        device.setUser(user);

        return DeviceMapper.toDTO(device);
    }

}
