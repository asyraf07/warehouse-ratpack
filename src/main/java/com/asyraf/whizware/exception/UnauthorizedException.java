package com.asyraf.whizware.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnauthorizedException extends RuntimeException {
    private Integer code;

    public UnauthorizedException(String message) {
        super(message);
        this.code = 401;
    }
}
