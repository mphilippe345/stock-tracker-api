package com.api.stocktracker.common.exception;

public class Conflict extends RuntimeException {

    public Conflict(String message) {
        super(message);
    }
}
