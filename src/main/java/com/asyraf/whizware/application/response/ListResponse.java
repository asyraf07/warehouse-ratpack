package com.asyraf.whizware.application.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ListResponse<T> extends BaseResponse {
    private List<T> data;
}
