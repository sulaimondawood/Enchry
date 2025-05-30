package com.dawood.enchry.dto.auth;

import lombok.Data;

@Data
public class RegisterRequestDTO {

    private String email;

    private String password;

    private String username;
}
