package com.asyraf.whizware;

import com.asyraf.whizware.application.ApiEndpoints;
import com.asyraf.whizware.infrastructure.filter.JwtFilter;
import com.asyraf.whizware.infrastructure.module.ApiModule;
import com.asyraf.whizware.infrastructure.module.ErrorModule;
import ratpack.core.server.RatpackServer;
import ratpack.guice.Guice;

public class WhizwareApplication {

    public static void main(String[] args) throws Exception {

        RatpackServer.start(server -> server
                .registry(Guice.registry(binding -> binding
                    .module(ErrorModule.class)
                    .module(ApiModule.class)
                ))
                .handlers(chain -> chain
                    .all(JwtFilter.class)
                    .insert(ApiEndpoints.class))
        );

    }

}
