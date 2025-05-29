package com.dawood.enchry.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {

    private String username;

    private String email;

    private String token;
}
