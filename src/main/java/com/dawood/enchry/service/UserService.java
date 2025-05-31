package com.dawood.enchry.service;

import com.dawood.enchry.exception.EmailAlreadyExists;
import com.dawood.enchry.exception.UserNotFoundException;
import com.dawood.enchry.model.User;
import com.dawood.enchry.repository.UserRepository;
import com.dawood.enchry.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public boolean existsByEmail(String email){
      return userRepository.existsByEmail(email);
    }

    public User findUserByEmail(String email){
        return  userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User credentials not found"));
    }

    public User extractUserFromToken(String jwt){
        String userEmail = jwtUtils.extractEmail(jwt);
        return findUserByEmail(userEmail);
    }
}
