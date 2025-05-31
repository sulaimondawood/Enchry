package com.dawood.enchry.mapper;

import com.dawood.enchry.dto.device.DeviceRequestResponseDTO;
import com.dawood.enchry.model.Device;

public class DeviceMapper {
//    public static Device toModel(DeviceRequestResponseDTO deviceRequestResponseDTO){
//        Device device = new Device();
//        device.setDeviceName(device.getDeviceName());
//        device.setDeviceDescription(device.getDeviceDescription());
//        device.setSensorType(device.getSensorType());
//        device.setUser();
//    }

    public static DeviceRequestResponseDTO toDTO(Device device){
        DeviceRequestResponseDTO dto = new DeviceRequestResponseDTO();

        dto.setDeviceName(device.getDeviceName());
        dto.setDeviceDescription(device.getDeviceDescription());
        dto.setSensorType(device.getSensorType());

        return dto;
    }
}
