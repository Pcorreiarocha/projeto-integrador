package com.faculdade.commons.support;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude( JsonInclude.Include.NON_EMPTY )
public class Response<T> {

    private T       data;
    private Boolean success;
    private Integer code;
    private String  message;

}