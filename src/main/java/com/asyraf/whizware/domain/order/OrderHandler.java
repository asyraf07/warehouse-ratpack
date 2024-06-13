package com.asyraf.whizware.domain.order;

import com.asyraf.whizware.application.dto.order.OrderRequest;
import com.google.inject.Inject;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;
import ratpack.core.jackson.Jackson;

public class OrderHandler implements Handler {

    @Inject
    private OrderService orderService;

    @Override
    public void handle(Context context) throws Exception {
        context.byMethod(byMethodSpec -> byMethodSpec
            .get(c -> c.render(Jackson.json(orderService.getAllOrder())))
            .post(c -> c.parse(OrderRequest.class)
                .then(request -> c.render(Jackson.json(orderService.addOrder(request)))))
        );
    }
}
