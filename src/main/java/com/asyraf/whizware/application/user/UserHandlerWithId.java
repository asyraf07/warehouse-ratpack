package com.asyraf.whizware.application.user;

import com.asyraf.whizware.domain.user.UserService;
import com.asyraf.whizware.infrastructure.constant.Role;
import com.asyraf.whizware.infrastructure.security.AuthUtil;
import com.asyraf.whizware.infrastructure.util.PathVariableUtil;
import com.google.inject.Inject;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;
import ratpack.core.jackson.Jackson;

import java.util.List;

public class UserHandlerWithId implements Handler {

    @Inject
    private UserService userService;

    @Override
    public void handle(Context context) throws Exception {
        context.byMethod(byMethodSpec -> byMethodSpec
            .get(c -> {
                AuthUtil.authorize(c, List.of(Role.SYSTEM, Role.ADMIN, Role.USER));
                c.render(Jackson.json(userService.getUser(PathVariableUtil.getUUID(c.getAllPathTokens(), "id"))));
            })
            .put(c -> {
                AuthUtil.authorize(c, List.of(Role.SYSTEM));
                c.parse(UserRequest.class)
                    .then(request -> c.render(Jackson.json(userService.updateUser(PathVariableUtil.getUUID(c.getAllPathTokens(), "id"), request))));
            })
            .delete(c -> {
                AuthUtil.authorize(c, List.of(Role.SYSTEM));
                userService.deleteUser(PathVariableUtil.getUUID(c.getAllPathTokens(), "id"));
                c.getResponse().status(201).send();
            })
        );
    }
}
