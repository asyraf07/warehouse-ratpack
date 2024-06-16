package com.asyraf.whizware.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ForbiddenException extends RuntimeException {
    private Integer code;

    public ForbiddenException(String message) {
        super(message);
        this.code = 403;
    }
}
