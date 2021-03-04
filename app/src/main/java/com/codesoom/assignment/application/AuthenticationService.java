package com.codesoom.assignment.application;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Service
public class AuthenticationService {

    public String login() {

        String secret="12345678901234567890123456789012";

        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .claim("userId", 1L)
                .signWith(key)
                .compact();
    }

}
