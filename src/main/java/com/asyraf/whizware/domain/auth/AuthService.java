package com.asyraf.whizware.domain.auth;

import com.asyraf.whizware.application.auth.LoginRequest;
import com.asyraf.whizware.application.auth.RegisterRequest;
import com.asyraf.whizware.infrastructure.response.NoDataResponse;
import com.asyraf.whizware.domain.user.User;
import com.asyraf.whizware.exception.BadRequestException;
import com.asyraf.whizware.infrastructure.constant.Role;
import com.asyraf.whizware.domain.user.UserBaseRepository;
import com.asyraf.whizware.infrastructure.security.JwtUtil;
import com.google.inject.Inject;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {

    @Inject
    private UserBaseRepository userRepository;

    public NoDataResponse register(RegisterRequest request, Role role) {
        userRepository.save(User.builder()
            .username(request.getUsername())
            .password(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()))
            .role(role)
            .build());
        return NoDataResponse.builder()
            .success(true)
            .message("User successfully registered!")
            .build();
    }

    public NoDataResponse login (LoginRequest request) {
        User user = userRepository.getByUsername(request.getUsername()).orElseThrow(() -> new BadRequestException("Username not found!"));

        if (!BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Wrong password!");
        }
        String token = JwtUtil.generateToken(user);
        return NoDataResponse.builder()
            .success(true)
            .message(token)
            .build();
    }

}
