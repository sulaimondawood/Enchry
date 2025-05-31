package com.dawood.enchry.dto.device;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceResponseDTO {
    private String id;

    private String deviceName;

    private String deviceDescription;

    private String sensorType;

    private boolean isActive;

}
