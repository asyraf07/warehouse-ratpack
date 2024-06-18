package com.asyraf.whizware.application.auth;

import com.asyraf.whizware.domain.auth.AuthService;
import com.google.inject.Inject;
import ratpack.core.handling.Chain;
import ratpack.core.jackson.Jackson;
import ratpack.func.Action;

public class LoginHandler implements Action<Chain> {
    @Inject
    private AuthService authService;

    @Override
    public void execute(Chain chain) {
        chain.post(context -> context.parse(LoginRequest.class).then(request -> context.render(Jackson.json(authService.login(request)))));
    }
}
