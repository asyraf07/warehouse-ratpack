package com.asyraf.whizware.domain.auth;

import com.asyraf.whizware.application.dto.auth.LoginRequest;
import com.asyraf.whizware.application.dto.auth.RegisterRequest;
import com.asyraf.whizware.application.response.NoDataResponse;
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

//
//    public NoDataResponse registerUser(RegisterRequest request) {
//        userRepository.save(User.builder()
//            .username(request.getUsername())
//            .password(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()))
//            .role(Role.USER)
//            .build());
//        return NoDataResponse.builder()
//            .success(true)
//            .message("User successfully registered!")
//            .build();
//    }
//
//    public NoDataResponse registerAdmin(RegisterRequest request) {
//        userRepository.save(User.builder()
//            .username(request.getUsername())
//            .password(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()))
//            .role(Role.ADMIN)
//            .build());
//        return NoDataResponse.builder()
//            .success(true)
//            .message("Admin successfully registered!")
//            .build();
//    }
//
//    public NoDataResponse registerSystemAdmin(RegisterRequest request) {
//        userRepository.save(User.builder()
//            .username(request.getUsername())
//            .password(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()))
//            .role(Role.SYSTEM)
//            .build());
//        return NoDataResponse.builder()
//            .success(true)
//            .message("System Admin successfully registered!")
//            .build();
//    }
//
}
