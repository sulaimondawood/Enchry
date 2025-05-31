package com.dawood.enchry.controller;

import com.dawood.enchry.dto.device.DeviceRequestResponseDTO;
import com.dawood.enchry.service.device.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private static final Logger log = LoggerFactory.getLogger(DeviceController.class);
    private final DeviceService deviceService;

    @PostMapping
    public ResponseEntity<DeviceRequestResponseDTO> addDevice(@Valid @RequestBody DeviceRequestResponseDTO req,
                                                              @RequestHeader("Authorization") String jwt){

        DeviceRequestResponseDTO res = deviceService.addDevice(req,jwt);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping
    public ResponseEntity<List<DeviceRequestResponseDTO>> getAllDevics(){
        List<DeviceRequestResponseDTO> devices = deviceService.getAllDevices();
        return ResponseEntity.ok().body(devices);
    }

    @GetMapping
    public ResponseEntity<List<DeviceRequestResponseDTO>> getMyDevices(){
        return null;
    }
}
