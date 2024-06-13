package com.asyraf.whizware.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BadRequestException extends RuntimeException {
    private Integer code;

    public BadRequestException(String message) {
        super(message);
        this.code = 400;
    }
}
