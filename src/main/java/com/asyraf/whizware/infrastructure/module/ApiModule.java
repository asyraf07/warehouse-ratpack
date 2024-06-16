package com.asyraf.whizware.infrastructure.module;

import com.asyraf.whizware.application.ApiEndpoints;
import com.asyraf.whizware.infrastructure.filter.JwtFilter;
import com.asyraf.whizware.application.dto.auth.LoginHandler;
import com.asyraf.whizware.application.dto.auth.RegisterHandler;
import com.asyraf.whizware.application.dto.item.ItemHandler;
import com.asyraf.whizware.application.dto.item.ItemHandlerWithId;
import com.asyraf.whizware.application.dto.order.OrderHandler;
import com.asyraf.whizware.application.dto.order.OrderHandlerWithId;
import com.asyraf.whizware.application.dto.user.UserHandler;
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

        bind(RegisterHandler.class);
        bind(LoginHandler.class);

        bind(JwtFilter.class);
    }
}
