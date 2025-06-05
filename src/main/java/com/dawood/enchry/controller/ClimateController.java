package com.dawood.enchry.controller;


import com.dawood.enchry.dto.climate.ClimateRequestDTO;
import com.dawood.enchry.dto.climate.ClimateResponseDTO;
import com.dawood.enchry.service.climate.ClimateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("climate")
@RequiredArgsConstructor
public class ClimateController {

    private final ClimateService climateService;

    @PostMapping
    public ResponseEntity<ClimateResponseDTO> create(@RequestBody ClimateRequestDTO req){
        ClimateResponseDTO response = climateService.create(req);
        return ResponseEntity.ok(response);
    }
}
