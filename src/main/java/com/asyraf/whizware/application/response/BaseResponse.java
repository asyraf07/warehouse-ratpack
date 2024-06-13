package com.asyraf.whizware.application.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class BaseResponse {
    private boolean success;
    private String message;
}
