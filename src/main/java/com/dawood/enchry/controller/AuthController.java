package com.dawood.enchry.controller;

import com.dawood.enchry.dto.auth.LoginRequestDTO;
import com.dawood.enchry.dto.auth.LoginResponseDTO;
import com.dawood.enchry.dto.auth.RegisterRequestDTO;
import com.dawood.enchry.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/")
@RequiredArgsConstructor
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDTO req){
        authService.register(req);
        return ResponseEntity.ok().body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@Valid @RequestBody LoginRequestDTO req){
        LoginResponseDTO response = authService.authenticate(req);
        return ResponseEntity.ok().body(response);
    }
}
