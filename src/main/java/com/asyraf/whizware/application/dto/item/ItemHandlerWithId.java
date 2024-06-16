package com.asyraf.whizware.application.dto.item;

import com.asyraf.whizware.domain.item.ItemService;
import com.asyraf.whizware.infrastructure.constant.Role;
import com.asyraf.whizware.infrastructure.security.AuthUtil;
import com.asyraf.whizware.infrastructure.util.PathVariableUtil;
import com.google.inject.Inject;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;
import ratpack.core.jackson.Jackson;

import java.util.List;

public class ItemHandlerWithId implements Handler {

    @Inject
    private ItemService itemService;

    @Override
    public void handle(Context context) throws Exception {
        context.byMethod(byMethodSpec -> byMethodSpec
            .get(c -> {
                AuthUtil.authorize(c, List.of(Role.SYSTEM, Role.ADMIN, Role.USER));
                c.render(Jackson.json(itemService.getItem(PathVariableUtil.getUUID(c.getAllPathTokens(), "id"))));
            })
            .put(c -> {
                AuthUtil.authorize(c, List.of(Role.SYSTEM, Role.ADMIN));
                c.parse(ItemRequest.class)
                    .then(request -> c.render(Jackson.json(itemService.updateItem(PathVariableUtil.getUUID(c.getAllPathTokens(), "id"), request))));
            })
            .delete(c -> {
                AuthUtil.authorize(c, List.of(Role.SYSTEM, Role.ADMIN));
                itemService.deleteItem(PathVariableUtil.getUUID(c.getAllPathTokens(), "id"));
                c.getResponse().status(201).send();
            })
        );
    }
}
