package com.dawood.enchry.dto.device;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceRequestDTO {

    @NotBlank(message = "Device name is required")
    private String deviceName;

    private String deviceDescription;

    @NotBlank(message = "Select sensor type")
    private String sensorType;
}
