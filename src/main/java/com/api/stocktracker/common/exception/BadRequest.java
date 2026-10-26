package com.api.stocktracker.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BadRequest extends RuntimeException {

    public BadRequest(String message) {
        super(message);
    }
}
