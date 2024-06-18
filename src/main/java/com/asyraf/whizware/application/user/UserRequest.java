package com.asyraf.whizware.application.user;

import com.asyraf.whizware.infrastructure.constant.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
