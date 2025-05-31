package com.dawood.enchry.controller;

import com.dawood.enchry.dto.device.DeviceRequestResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    public ResponseEntity<DeviceRequestResponseDTO> addDevice(){
        return null;
    }
}
