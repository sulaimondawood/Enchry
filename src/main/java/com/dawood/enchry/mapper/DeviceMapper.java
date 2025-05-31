package com.dawood.enchry.mapper;

import com.dawood.enchry.dto.device.DeviceResponseDTO;
import com.dawood.enchry.model.Device;

public class DeviceMapper {
//    public static Device toModel(DeviceRequestResponseDTO deviceRequestResponseDTO){
//        Device device = new Device();
//        device.setDeviceName(device.getDeviceName());
//        device.setDeviceDescription(device.getDeviceDescription());
//        device.setSensorType(device.getSensorType());
//        device.setUser();
//    }

    public static DeviceResponseDTO toDTO(Device device){
        DeviceResponseDTO dto = new DeviceResponseDTO();

        dto.setId(device.getId().toString());
        dto.setDeviceName(device.getDeviceName());
        dto.setDeviceDescription(device.getDeviceDescription());
        dto.setSensorType(device.getSensorType());
        dto.setActive(device.isActive());

        return dto;
    }
}
