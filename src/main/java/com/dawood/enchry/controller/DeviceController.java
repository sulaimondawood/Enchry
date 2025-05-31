package com.dawood.enchry.controller;

import com.dawood.enchry.dto.device.DeviceRequestResponseDTO;
import com.dawood.enchry.service.device.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping
    public ResponseEntity<DeviceRequestResponseDTO> addDevice(@Valid @RequestBody DeviceRequestResponseDTO req,
                                                              @RequestHeader("Authorization") String jwt){

        DeviceRequestResponseDTO res = deviceService.addDevice(req,jwt);
        return ResponseEntity.created(new URI().getPath()).body(res);
    }
}
