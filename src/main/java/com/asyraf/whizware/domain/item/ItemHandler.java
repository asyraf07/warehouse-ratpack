package com.asyraf.whizware.domain.item;

import com.asyraf.whizware.application.dto.item.ItemRequest;
import com.google.inject.Inject;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;
import ratpack.core.jackson.Jackson;

public class ItemHandler implements Handler {

    @Inject
    private ItemService itemService;

    @Override
    public void handle(Context context) throws Exception {
        context.byMethod(byMethodSpec -> byMethodSpec
            .get(c -> c.render(Jackson.json(itemService.getAllItem())))
            .post(c -> c.parse(ItemRequest.class)
                .then(request -> c.render(Jackson.json(itemService.addItem(request)))))
        );
    }
}
