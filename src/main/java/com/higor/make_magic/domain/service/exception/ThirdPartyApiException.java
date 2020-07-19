package com.higor.make_magic.domain.service.exception;

public class ThirdPartyApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ThirdPartyApiException(String message) {
        super(message);
    }
}