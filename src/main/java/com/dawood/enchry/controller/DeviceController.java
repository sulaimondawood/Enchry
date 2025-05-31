package com.dawood.enchry.controller;

import com.dawood.enchry.dto.device.DeviceRequestResponseDTO;
import com.dawood.enchry.service.device.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private static final Logger log = LoggerFactory.getLogger(DeviceController.class);
    private final DeviceService deviceService;

    @PostMapping
    public ResponseEntity<DeviceRequestResponseDTO> addDevice(@Valid @RequestBody DeviceRequestResponseDTO req,
                                                              @RequestHeader("Authorization") String jwt){

        System.out.println("Add device controller");
        log.info("Add device controller"+jwt);
        DeviceRequestResponseDTO res = deviceService.addDevice(req,jwt);
        return ResponseEntity.ok().body(res);
    }
}
