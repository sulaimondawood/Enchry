package com.dawood.enchry.utils;

import io.jsonwebtoken.JwtException;
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
        String secrets = "rjgrkjvrh48u38yrhefhjefnvdmekfekfnerneruiiejdkvndmveirunfmd";
        byte[] bytes = secrets.getBytes(StandardCharsets.UTF_8);
        this.secret = Keys.hmacShaKeyFor(bytes);
    }


    public String generateToken(String email, String fullname){
       return Jwts.builder()
                .subject(email)
                .claim("fullname", fullname)
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

    public boolean isTokenValid(String token){
        try {
            Jwts.parser()
                    .verifyWith(secret)
                    .build()
                    .parseSignedClaims(token);

            return true;
        }catch (JwtException ex){
            return false;
        }
    }

}
