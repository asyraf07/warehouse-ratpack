package com.asyraf.whizware.domain.order;

import com.asyraf.whizware.application.dto.order.OrderRequest;
import com.asyraf.whizware.util.PathVariableUtil;
import com.google.inject.Inject;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;
import ratpack.core.jackson.Jackson;

public class OrderHandlerWithId implements Handler {

    @Inject
    private OrderService orderService;

    @Override
    public void handle(Context context) throws Exception {
        context.byMethod(byMethodSpec -> byMethodSpec
            .get(c -> c.render(Jackson.json(orderService.getOrder(PathVariableUtil.getUUID(c.getAllPathTokens(), "id")))))
            .put(c -> c.parse(OrderRequest.class)
                .then(request -> c.render(Jackson.json(orderService.updateOrder(PathVariableUtil.getUUID(c.getAllPathTokens(), "id"), request)))))
            .delete(c -> {
                orderService.deleteOrder(PathVariableUtil.getUUID(c.getAllPathTokens(), "id"));
                c.getResponse().status(201).send();
            })
        );
    }
}
