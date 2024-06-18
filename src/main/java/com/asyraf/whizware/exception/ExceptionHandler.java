package com.asyraf.whizware.exception;

import com.asyraf.whizware.infrastructure.response.NoDataResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.jsonwebtoken.lang.Strings;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.core.error.internal.ErrorHandler;
import ratpack.core.handling.Context;
import ratpack.core.parse.NoSuchParserException;

public class ExceptionHandler implements ErrorHandler {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    private final ObjectMapper mapper;

    public ExceptionHandler() {
        this.mapper = new ObjectMapper();
        this.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public void error(Context context, int statusCode) throws Exception {
        log.error("[START] CLIENT ERROR:");
        context.getResponse().status(statusCode).send();
        log.error("[END] CLIENT ERROR: {}", statusCode);
    }

    @Override
    public void error(Context context, Throwable throwable) throws Exception {
        log.error("[START] SERVER ERROR: ");
        throwable.printStackTrace();
        log.error("[END] SERVER ERROR: {}", throwable.getMessage());

        context.getResponse().getHeaders().set("Content-type", "application/json");

        NoDataResponse response = NoDataResponse.builder()
            .success(false)
            .message(throwable.getMessage())
            .build();

        if (throwable instanceof BadRequestException err) {
            context.getResponse().status(err.getCode());
        } else if (throwable instanceof NotFoundException err) {
            context.getResponse().status(err.getCode());
        } else if (throwable instanceof UnauthorizedException err) {
            context.getResponse().status(err.getCode());
        } else if (throwable instanceof ForbiddenException err) {
            context.getResponse().status(err.getCode());
        } else if (throwable instanceof JsonParseException || throwable instanceof NoSuchParserException) {
            context.getResponse().status(400);
            response.setMessage("Request body is not valid!");
        } else if (throwable instanceof InvalidFormatException err) {
            context.getResponse().status(400);
            response.setMessage(String.format("%s is not a valid %s", err.getValue().toString(), err.getTargetType().getSimpleName()));
        } else if (throwable instanceof ConstraintViolationException err) {
            context.getResponse().status(400);
            response.setMessage(String.format("%s already exists", Strings.capitalize(err.getConstraintName())));
        } else {
            context.getResponse().status(500);
        }
        context.getResponse().send(mapper.writeValueAsString(response));
    }

}
