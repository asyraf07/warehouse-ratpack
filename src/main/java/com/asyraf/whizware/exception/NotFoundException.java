package com.asyraf.whizware.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotFoundException extends Exception {
    private Integer code;

    public NotFoundException(String message) {
        super(message);
        this.code = 404;
    }
}
