package com.dawood.enchry.dto.device;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceRequestResponseDTO {
    @Column(nullable = false)
    @NotBlank(message = "Device name is required")
    private String deviceName;

    @Column(nullable = true)
    private String deviceDescription;

    @Column(nullable = false)
    @NotBlank(message = "Select sensor type")
    private String sensorType;
}
