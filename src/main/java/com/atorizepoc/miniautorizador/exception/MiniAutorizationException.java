package com.atorizepoc.miniautorizador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MiniAutorizationException extends Exception {

    public MiniAutorizationException(String message, Exception exception) {
        super(message, exception);
    }

    public MiniAutorizationException(MiniAutorizationErrors message) {
        super(message.getMessage());
    }
}
