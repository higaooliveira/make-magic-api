package com.higor.make_magic.domain.service.exception;

public class InvalidHouseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidHouseException(String message) {
        super(message);
    }
}