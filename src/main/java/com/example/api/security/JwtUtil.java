package com.example.api.security;



import org.springframework.stereotype.Component;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static javax.crypto.Cipher.SECRET_KEY;

@Component

public class JwtUtil {

    private final String secretKey = "MyTestSecretKey12345";

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }



    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }


    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(String.valueOf(SECRET_KEY))
                .parseClaimsJws(token)
                .getBody();
    }


    public boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }


    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }


}