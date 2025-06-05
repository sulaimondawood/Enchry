package com.dawood.enchry.controller;


import com.dawood.enchry.dto.climate.ClimateRequestDTO;
import com.dawood.enchry.dto.climate.ClimateResponseDTO;
import com.dawood.enchry.service.climate.ClimateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/latest")
    public ResponseEntity<ClimateResponseDTO> latestCLimateReading(){
        return ResponseEntity.ok(climateService.latestClimateReading());
    }
}
