package com.dawood.enchry.dto.climate;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClimateResponseDTO {

    private String sensoredData;

    private String nonce;

    private String salt;

    private String time;

    private String timezone;

    private Long longitude;

    private Long latitude;

}
