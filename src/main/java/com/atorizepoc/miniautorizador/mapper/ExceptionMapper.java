package com.atorizepoc.miniautorizador.mapper;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class ExceptionMapper {

    public String getRootCauseMessage(Throwable e) {
        Throwable cause = e.getCause();
        boolean hasMessage = cause != null
                && cause.getMessage() != null
                && !cause.getMessage().isBlank();
        if (hasMessage) {
            return getRootCauseMessage(cause);
        }
        return e.getMessage();
    }

    @SneakyThrows
    public Exception isInstanceOfExcepition(RuntimeException e) {
        if (e instanceof Exception) {
            return e;
        }
        return new Exception(e.toString());
    }

}
