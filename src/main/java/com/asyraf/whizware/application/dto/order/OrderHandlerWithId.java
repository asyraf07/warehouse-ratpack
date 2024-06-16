package com.asyraf.whizware.application.dto.order;

import com.asyraf.whizware.domain.order.OrderService;
import com.asyraf.whizware.infrastructure.constant.Role;
import com.asyraf.whizware.infrastructure.security.AuthUtil;
import com.asyraf.whizware.infrastructure.util.PathVariableUtil;
import com.google.inject.Inject;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;
import ratpack.core.jackson.Jackson;

import java.util.List;

public class OrderHandlerWithId implements Handler {

    @Inject
    private OrderService orderService;

    @Override
    public void handle(Context context) throws Exception {
        context.byMethod(byMethodSpec -> byMethodSpec
            .get(c -> {
                AuthUtil.authorize(c, List.of(Role.SYSTEM, Role.ADMIN, Role.USER));
                c.render(Jackson.json(orderService.getOrder(PathVariableUtil.getUUID(c.getAllPathTokens(), "id"))));
            })
            .put(c -> {
                AuthUtil.authorize(c, List.of(Role.SYSTEM, Role.ADMIN, Role.USER));
                c.parse(OrderRequest.class)
                    .then(request -> c.render(Jackson.json(orderService.updateOrder(PathVariableUtil.getUUID(c.getAllPathTokens(), "id"), request))));
            })
            .delete(c -> {
                AuthUtil.authorize(c, List.of(Role.SYSTEM, Role.ADMIN));
                orderService.deleteOrder(PathVariableUtil.getUUID(c.getAllPathTokens(), "id"));
                c.getResponse().status(201).send();
            })
        );
    }
}
