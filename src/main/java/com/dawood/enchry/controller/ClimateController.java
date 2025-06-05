package com.dawood.enchry.controller;


import com.dawood.enchry.dto.climate.ClimateResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/climate")
public class ClimateController {

    @PostMapping
    public ResponseEntity<ClimateResponseDTO> create(@RequestBody String req){
        return
    }
}
