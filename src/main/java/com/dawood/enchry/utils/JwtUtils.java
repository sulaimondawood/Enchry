package com.dawood.enchry.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {
    private final int expiration =86400000;
    private final SecretKey secret;

    public JwtUtils( @Value("{{jwt.secret}}") String secret){
        byte[] bytes = Base64.getDecoder().decode(secret.getBytes(StandardCharsets.UTF_8));
        this.secret = Keys.hmacShaKeyFor(bytes);
    }


    public String generateToken(String email, String username){
       return Jwts.builder()
                .subject(email)
                .claim("username", username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ expiration))
                .signWith(secret)
                .compact();
    }

    public String extractEmail(String token){
       return Jwts.parser()
                .verifyWith(secret)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }

}
