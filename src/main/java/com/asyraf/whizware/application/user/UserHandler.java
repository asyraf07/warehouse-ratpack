package com.asyraf.whizware.application.user;

import com.asyraf.whizware.domain.user.UserService;
import com.asyraf.whizware.infrastructure.constant.Role;
import com.asyraf.whizware.infrastructure.security.AuthUtil;
import com.google.inject.Inject;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;
import ratpack.core.jackson.Jackson;

import java.util.List;

public class UserHandler implements Handler {

    @Inject
    private UserService userService;

    @Override
    public void handle(Context context) throws Exception {
        context.byMethod(byMethodSpec -> byMethodSpec
            .get(c -> {
                AuthUtil.authorize(c, List.of(Role.SYSTEM, Role.ADMIN, Role.USER));
                c.render(Jackson.json(userService.getAllUser()));
            })
            .post(c -> {
                AuthUtil.authorize(c, List.of(Role.SYSTEM));
                c.parse(UserRequest.class).then(request -> c.render(Jackson.json(userService.addUser(request))));
            })
        );
    }
}
