package com.asyraf.whizware.application.dto.auth;

import com.asyraf.whizware.domain.auth.AuthService;
import com.asyraf.whizware.infrastructure.constant.Role;
import com.google.inject.Inject;
import ratpack.core.handling.Chain;
import ratpack.core.jackson.Jackson;
import ratpack.func.Action;

public class RegisterHandler implements Action<Chain> {
    @Inject
    private AuthService authService;

    @Override
    public void execute(Chain chain) {
        chain.post("user", context -> context.parse(RegisterRequest.class).then(request -> context.render(Jackson.json(authService.register(request, Role.USER)))));
        chain.post("admin", context -> context.parse(RegisterRequest.class).then(request -> context.render(Jackson.json(authService.register(request, Role.ADMIN)))));
        chain.post("sysadmin", context -> context.parse(RegisterRequest.class).then(request -> context.render(Jackson.json(authService.register(request, Role.SYSTEM)))));
    }
}
