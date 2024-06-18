package com.asyraf.whizware.application;

import com.asyraf.whizware.application.auth.LoginHandler;
import com.asyraf.whizware.application.auth.RegisterHandler;
import com.asyraf.whizware.application.item.ItemHandler;
import com.asyraf.whizware.application.item.ItemHandlerWithId;
import com.asyraf.whizware.application.order.OrderHandler;
import com.asyraf.whizware.application.order.OrderHandlerWithId;
import com.asyraf.whizware.application.user.UserHandler;
import com.asyraf.whizware.application.user.UserHandlerWithId;
import ratpack.core.handling.Chain;
import ratpack.func.Action;

public class ApiEndpoints implements Action<Chain> {

    @Override
    public void execute(Chain chain) throws Exception {
        chain.path("item", ItemHandler.class);
        chain.path("item/:id", ItemHandlerWithId.class);

        chain.path("order", OrderHandler.class);
        chain.path("order/:id", OrderHandlerWithId.class);

        chain.path("user", UserHandler.class);
        chain.path("user/:id", UserHandlerWithId.class);

        chain.prefix("login", LoginHandler.class);
        chain.prefix("register", RegisterHandler.class);
    }
}
