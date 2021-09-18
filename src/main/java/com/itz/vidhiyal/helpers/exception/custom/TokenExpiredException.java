package com.itz.vidhiyal.helpers.exception.custom;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TokenExpiredException extends RuntimeException {
    private String message;

    public TokenExpiredException(String message) {
        super();
        this.message = message;
    }
}
