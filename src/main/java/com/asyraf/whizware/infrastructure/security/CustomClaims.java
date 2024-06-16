package com.asyraf.whizware.infrastructure.security;

import com.asyraf.whizware.infrastructure.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomClaims {
    private String username;
    private Role role;
    private Date expiration;
}
