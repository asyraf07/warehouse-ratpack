package com.asyraf.whizware.application.dto.user;

import com.asyraf.whizware.infrastructure.constant.Role;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserDto {
    private UUID id;
    private String username;
    private Role role;
}