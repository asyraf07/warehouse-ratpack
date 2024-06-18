package com.asyraf.whizware.application.order;

import com.asyraf.whizware.domain.order.OrderService;
import com.asyraf.whizware.infrastructure.constant.Role;
import com.asyraf.whizware.infrastructure.security.AuthUtil;
import com.google.inject.Inject;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;
import ratpack.core.jackson.Jackson;

import java.util.List;

public class OrderHandler implements Handler {

    @Inject
    private OrderService orderService;

    @Override
    public void handle(Context context) throws Exception {
        context.byMethod(byMethodSpec -> byMethodSpec
            .get(c -> {
                AuthUtil.authorize(c, List.of(Role.SYSTEM, Role.ADMIN, Role.USER));
                c.render(Jackson.json(orderService.getAllOrder()));
            })
            .post(c -> {
                AuthUtil.authorize(c, List.of(Role.SYSTEM, Role.USER));
                c.parse(OrderRequest.class)
                    .then(request -> c.render(Jackson.json(orderService.addOrder(request))));
            })
        );
    }
}
