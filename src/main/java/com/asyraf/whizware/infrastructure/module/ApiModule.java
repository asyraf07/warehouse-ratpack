package com.asyraf.whizware.infrastructure.module;

import com.asyraf.whizware.application.ApiEndpoints;
import com.asyraf.whizware.domain.item.ItemHandler;
import com.asyraf.whizware.domain.item.ItemHandlerWithId;
import com.asyraf.whizware.domain.order.OrderHandler;
import com.asyraf.whizware.domain.order.OrderHandlerWithId;
import com.google.inject.AbstractModule;

public class ApiModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ApiEndpoints.class);

        bind(ItemHandler.class);
        bind(ItemHandlerWithId.class);

        bind(OrderHandler.class);
        bind(OrderHandlerWithId.class);
    }
}
