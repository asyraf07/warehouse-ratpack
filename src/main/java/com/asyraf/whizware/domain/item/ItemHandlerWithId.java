package com.asyraf.whizware.domain.item;

import com.asyraf.whizware.application.dto.item.ItemRequest;
import com.asyraf.whizware.util.PathVariableUtil;
import com.google.inject.Inject;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;
import ratpack.core.jackson.Jackson;

public class ItemHandlerWithId implements Handler {

    @Inject
    private ItemService itemService;

    @Override
    public void handle(Context context) throws Exception {
        context.byMethod(byMethodSpec -> byMethodSpec
            .get(c -> c.render(Jackson.json(itemService.getItem(PathVariableUtil.getUUID(c.getAllPathTokens(), "id")))))
            .put(c -> c.parse(ItemRequest.class)
                .then(request -> c.render(Jackson.json(itemService.updateItem(PathVariableUtil.getUUID(c.getAllPathTokens(), "id"), request)))))
            .delete(c -> {
                itemService.deleteItem(PathVariableUtil.getUUID(c.getAllPathTokens(), "id"));
                c.getResponse().status(201).send();
            })
        );
    }
}
