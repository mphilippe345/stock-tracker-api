package com.api.stocktracker.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotFound extends RuntimeException {

    public NotFound(String message) {
        super(message);
    }
}
