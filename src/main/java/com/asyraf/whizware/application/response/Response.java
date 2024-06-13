package com.asyraf.whizware.application.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Response<T> extends BaseResponse {
    private T data;
}
