package com.asyraf.whizware.infrastructure.module;

import com.asyraf.whizware.exception.ExceptionHandler;
import com.google.inject.AbstractModule;
import ratpack.core.error.ClientErrorHandler;
import ratpack.core.error.ServerErrorHandler;

public class ErrorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ClientErrorHandler.class).to(ExceptionHandler.class);
        bind(ServerErrorHandler.class).to(ExceptionHandler.class);
    }
}
