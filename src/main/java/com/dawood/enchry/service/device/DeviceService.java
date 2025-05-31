package com.dawood.enchry.service.device;

import com.dawood.enchry.dto.device.DeviceRequestResponseDTO;
import com.dawood.enchry.model.Device;
import com.dawood.enchry.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceRequestResponseDTO addDevice(DeviceRequestResponseDTO req){
        Device device = new Device();


        device.setDeviceName(req.getDeviceName());
        device.setSensorType(req.getSensorType());
        device.set
    }

}
