package com.asyraf.whizware.application;

import com.asyraf.whizware.domain.item.ItemHandler;
import com.asyraf.whizware.domain.item.ItemHandlerWithId;
import com.asyraf.whizware.domain.order.OrderHandler;
import com.asyraf.whizware.domain.order.OrderHandlerWithId;
import ratpack.core.handling.Chain;
import ratpack.func.Action;

public class ApiEndpoints implements Action<Chain> {

    @Override
    public void execute(Chain chain) {
        chain.path("item", ItemHandler.class);
        chain.path("item/:id", ItemHandlerWithId.class);

        chain.path("order", OrderHandler.class);
        chain.path("order/:id", OrderHandlerWithId.class);
    }
}
