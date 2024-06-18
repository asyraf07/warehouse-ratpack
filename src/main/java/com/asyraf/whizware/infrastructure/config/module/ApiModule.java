package com.asyraf.whizware.infrastructure.config.module;

import com.asyraf.whizware.application.ApiEndpoints;
import com.asyraf.whizware.application.user.UserHandlerWithId;
import com.asyraf.whizware.infrastructure.config.filter.JwtFilter;
import com.asyraf.whizware.application.auth.LoginHandler;
import com.asyraf.whizware.application.auth.RegisterHandler;
import com.asyraf.whizware.application.item.ItemHandler;
import com.asyraf.whizware.application.item.ItemHandlerWithId;
import com.asyraf.whizware.application.order.OrderHandler;
import com.asyraf.whizware.application.order.OrderHandlerWithId;
import com.asyraf.whizware.application.user.UserHandler;
import com.google.inject.AbstractModule;

public class ApiModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ApiEndpoints.class);

        bind(ItemHandler.class);
        bind(ItemHandlerWithId.class);

        bind(OrderHandler.class);
        bind(OrderHandlerWithId.class);

        bind(UserHandler.class);
        bind(UserHandlerWithId.class);

        bind(RegisterHandler.class);
        bind(LoginHandler.class);

        bind(JwtFilter.class);
    }
}
