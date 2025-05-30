package com.dawood.enchry.service;

import com.dawood.enchry.dto.auth.RegisterRequestDTO;
import com.dawood.enchry.model.User;
import com.dawood.enchry.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequestDTO requestDTO){
        userService.findUserByEmail(requestDTO.getEmail());

        User newUser = new User();
        newUser.setEmail(requestDTO.getEmail());
        newUser.setUsername(requestDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(requestDTO.getPassword()));

        userRepository.save(newUser);
    }
}
