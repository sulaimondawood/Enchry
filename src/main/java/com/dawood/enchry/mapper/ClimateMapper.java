package com.dawood.enchry.mapper;

import com.dawood.enchry.dto.climate.ClimateResponseDTO;
import com.dawood.enchry.model.Climate;

public class ClimateMapper {

    public static ClimateResponseDTO toDTO(Climate climate){
        ClimateResponseDTO responseDTO = new ClimateResponseDTO();

        responseDTO.setLatitude(climate.getLatitude());
        responseDTO.setTime(climate.getTime());
        responseDTO.setSalt(climate.getSalt());
        responseDTO.setNonce(climate.getNonce());
        responseDTO.setLongitude(climate.getLongitude());
        responseDTO.setTimezone(climate.getTimezone());
        responseDTO.setSensoredData(climate.getSensoredData());

        return  responseDTO;
    }

//    public static Climate toModel(ClimateResponseDTO responseDTO){
//        Climate climate = new Climate();
//
//        climate.set
//    }
}
