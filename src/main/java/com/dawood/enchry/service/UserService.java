package com.dawood.enchry.service;

import com.dawood.enchry.exception.EmailAlreadyExists;
import com.dawood.enchry.model.User;
import com.dawood.enchry.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUserByEmail(String email){
        if(userRepository.findByEmail(email).isPresent()){
            throw new EmailAlreadyExists("Email already exist;");
        }
    }
}
