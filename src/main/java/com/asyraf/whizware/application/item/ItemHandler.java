package com.asyraf.whizware.application.item;

import com.asyraf.whizware.domain.item.ItemService;
import com.asyraf.whizware.infrastructure.constant.Role;
import com.asyraf.whizware.infrastructure.security.AuthUtil;
import com.google.inject.Inject;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;
import ratpack.core.jackson.Jackson;

import java.util.List;

public class ItemHandler implements Handler {

    @Inject
    private ItemService itemService;

    @Override
    public void handle(Context context) throws Exception {
        context.byMethod(byMethodSpec -> byMethodSpec
            .get(c -> {
                AuthUtil.authorize(c, List.of(Role.SYSTEM, Role.ADMIN, Role.USER));
                c.render(Jackson.json(itemService.getAllItem()));
            })
            .post(c -> {
                AuthUtil.authorize(c, List.of(Role.SYSTEM, Role.ADMIN));
                c.parse(ItemRequest.class).then(request -> c.render(Jackson.json(itemService.addItem(request))));
            })
        );
    }
}
