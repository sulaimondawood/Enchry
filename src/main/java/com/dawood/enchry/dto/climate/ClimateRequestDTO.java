package com.dawood.enchry.dto.climate;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ClimateRequestDTO {

    private String sensoredData;

    private String nonce;

    private String salt;

    private String time;

    private UUID deviceId;

    private String timezone;

    private Long longitude;

    private Long latitude;

}
