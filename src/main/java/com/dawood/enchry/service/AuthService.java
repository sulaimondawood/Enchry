package com.dawood.enchry.service;

import com.dawood.enchry.dto.auth.LoginRequestDTO;
import com.dawood.enchry.dto.auth.LoginResponseDTO;
import com.dawood.enchry.dto.auth.RegisterRequestDTO;
import com.dawood.enchry.exception.EmailAlreadyExists;
import com.dawood.enchry.exception.IncorrectEmailPasswordException;
import com.dawood.enchry.model.User;
import com.dawood.enchry.repository.UserRepository;
import com.dawood.enchry.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public void register(RegisterRequestDTO requestDTO){
        if(userService.existsByEmail(requestDTO.getEmail())){
            throw new EmailAlreadyExists("Email already exists");
        }

        User newUser = new User();
        newUser.setEmail(requestDTO.getEmail());
        newUser.setFullname(requestDTO.getFullname());
        newUser.setPassword(passwordEncoder.encode(requestDTO.getPassword()));

        userRepository.save(newUser);
    }

    public LoginResponseDTO authenticate(LoginRequestDTO req){
        LoginResponseDTO response = new LoginResponseDTO();

        User user= userService.findUserByEmail(req.getEmail());

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(),req.getPassword()));
        if(authentication.isAuthenticated()){
            response.setEmail(req.getEmail());
            response.setToken(jwtUtils.generateToken(req.getEmail(), user.getFullname()));

            return  response;
        }

        throw new IncorrectEmailPasswordException("Invalid credentials");

    }
}
