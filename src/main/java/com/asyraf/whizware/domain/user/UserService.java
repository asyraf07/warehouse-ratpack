package com.asyraf.whizware.domain.user;

import com.asyraf.whizware.application.dto.user.UserDto;
import com.asyraf.whizware.application.dto.user.UserRequest;
import com.asyraf.whizware.application.response.ListResponse;
import com.asyraf.whizware.application.response.Response;
import com.asyraf.whizware.exception.BadRequestException;
import com.asyraf.whizware.domain.order.OrderBaseRepository;
import com.google.inject.Inject;

import java.util.UUID;
import java.util.stream.Collectors;

public class UserService {

    @Inject
    UserBaseRepository userRepository;

    @Inject
    OrderBaseRepository orderRepository;

    public ListResponse<UserDto> getAllUser() {
        return ListResponse.<UserDto>builder()
            .success(true)
            .message("Berhasil!")
            .data(userRepository.getAll().stream().map(User::toDto).collect(Collectors.toList()))
            .build();
    }

    public Response<UserDto> getUser(UUID id) throws BadRequestException {
        return Response.<UserDto>builder()
            .success(true)
            .message("Berhasil!")
            .data(userRepository.get(id).orElseThrow(() -> new BadRequestException("Not found!")).toDto())
            .build();
    }

    public Response<UserDto> addUser(UserRequest request) throws Exception {
        return Response.<UserDto>builder()
            .success(true)
            .message("Berhasil!")
            .data(userRepository.save(User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .role(request.getRole())
                .build()).toDto())
            .build();
    }

    public Response<UserDto> updateUser(UUID id, UserRequest request) throws Exception {
        User user = userRepository.get(id).orElseThrow(() -> new BadRequestException("User not found!"));
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        return Response.<UserDto>builder()
            .success(true)
            .message("Berhasil!")
            .data(userRepository.save(user).toDto())
            .build();
    }

    public void deleteUser(UUID id) {
        User user = userRepository.get(id).orElseThrow(() -> new BadRequestException("User not found!"));
        if (!orderRepository.getByUserId(user.getId()).isEmpty()) {
            throw new BadRequestException("User is used in Order");
        }
        userRepository.delete(user);
    }

}
