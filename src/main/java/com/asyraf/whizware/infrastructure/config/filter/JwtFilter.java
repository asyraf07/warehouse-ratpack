package com.asyraf.whizware.infrastructure.config.filter;

import com.asyraf.whizware.infrastructure.security.CustomClaims;
import com.asyraf.whizware.infrastructure.security.JwtUtil;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;

public class JwtFilter implements Handler {
    @Override
    public void handle(Context context) {
        String authHeader = context.getRequest().getHeaders().get("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            JwtUtil.isTokenValid(token);
            CustomClaims claims = JwtUtil.extractClaims(token);
            context.getRequest().add(CustomClaims.class, claims);
        }
        context.next();
    }
}
