package com.asyraf.whizware.infrastructure.security;

import com.asyraf.whizware.domain.user.User;
import com.asyraf.whizware.exception.UnauthorizedException;
import com.asyraf.whizware.infrastructure.constant.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import java.util.*;

public class JwtUtil {
    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    private static final String SECRET_KEY = "whizwareapplicationforinventorytomanageyouritemsandorders";
    private static final Long EXPIRATION_TIME = 1000L * 60L * 10L; // 10 Minutes

    public static String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("role", user.getRole());
        return Jwts.builder()
            .subject(user.getUsername())
            .claims(claims)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(getSigningKey())
            .compact();
    }

    private static SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

    public static CustomClaims extractClaims(String token) {
        Claims claims = Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();

        return CustomClaims.builder()
            .username(claims.get("username", String.class))
            .role(Role.valueOf(claims.get("role", String.class)))
            .expiration(claims.getExpiration())
            .build();
    }

    public static void isTokenValid(String token) {
        try {
            extractClaims(token);
        } catch (ExpiredJwtException e) {
            log.error("[ERROR] isTokenExpired: {}", e.getMessage());
            throw new UnauthorizedException("Token has expired");
        } catch (Exception e) {
            log.error("[ERROR] isTokenValid: {}", e.getMessage());
            throw new UnauthorizedException("Token is invalid");
        }
    }

}
