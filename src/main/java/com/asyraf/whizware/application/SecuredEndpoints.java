package com.asyraf.whizware.application;

import ratpack.func.Action;
import ratpack.core.handling.Chain;

public class SecuredEndpoints implements Action<Chain> {

    @Override
    public void execute(Chain chain) throws Exception {
        chain.prefix("secure", c -> {
            c
                .get(context -> context.render("Hehehehe SECURED"));
        });
    }
}
