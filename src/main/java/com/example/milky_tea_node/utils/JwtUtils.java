package com.example.milky_tea_node.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private final String secretKey = "+jgdAFs8iWuMKnezI1ap8yMqa29Ck/1bDKXUQI7cKlU=";
    private final long expiration = 3600000;

    public String generateToken(String userId) {
        System.out.println(secretKey + ' ' + expiration);
        logger.info("Secret Key: {}", secretKey);
        logger.info("Expiration: {}", expiration);
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (expiration * 24 * 3)))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}