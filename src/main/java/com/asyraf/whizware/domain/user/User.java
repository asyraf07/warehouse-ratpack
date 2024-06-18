package com.asyraf.whizware.domain.user;

import com.asyraf.whizware.application.user.UserDto;
import com.asyraf.whizware.infrastructure.constant.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_user", uniqueConstraints = @UniqueConstraint(name = "username", columnNames = "username"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserDto toDto() {
        return UserDto.builder()
            .id(this.id)
            .username(this.username)
            .role(this.role)
            .build();
    }

}
