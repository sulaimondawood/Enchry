package com.dawood.enchry.controller;

import com.dawood.enchry.dto.device.DeviceRequestDTO;
import com.dawood.enchry.dto.device.DeviceResponseDTO;
import com.dawood.enchry.service.device.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private static final Logger log = LoggerFactory.getLogger(DeviceController.class);
    private final DeviceService deviceService;

    @PostMapping
    public ResponseEntity<DeviceResponseDTO> addDevice(@Valid @RequestBody DeviceRequestDTO req,
                                                              @RequestHeader("Authorization") String jwt){

        DeviceResponseDTO res = deviceService.addDevice(req,jwt);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping
    public ResponseEntity<List<DeviceResponseDTO>> getAllDevices(){
        List<DeviceResponseDTO> devices = deviceService.getAllDevices();
        return ResponseEntity.ok().body(devices);
    }

    @GetMapping("/user")
    public ResponseEntity<List<DeviceResponseDTO>> getUserDevices(@RequestHeader("Authorization") String jwt){

        List<DeviceResponseDTO> response = deviceService.getUserDevices(jwt);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{device_id}")
    public ResponseEntity<DeviceResponseDTO> deleteDevice(@RequestHeader("Authorization") String jwt,
                                                                 @PathVariable UUID device_id){
        deviceService.deleteDevice(jwt,device_id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{device_id}/status")
    public ResponseEntity<DeviceResponseDTO> changeDeviceStatus(
            @RequestParam boolean activate,
            @RequestHeader("Authorization") String jwt,
            @PathVariable UUID deviceId
    ){

        DeviceResponseDTO response = deviceService.changeDeviceStatus(deviceId,activate,jwt);
        return ResponseEntity.ok(response);
    }
}
