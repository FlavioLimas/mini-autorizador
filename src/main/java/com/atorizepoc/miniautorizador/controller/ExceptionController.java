package com.atorizepoc.miniautorizador.controller;

import com.atorizepoc.miniautorizador.mapper.ExceptionMapper;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionController extends ResponseEntityExceptionHandler {

    private ExceptionMapper exceptionMapper;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Error operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Object.class)))
    })
    @ExceptionHandler
    public ResponseEntity<Object> exceptionHandler(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(exceptionMapper.fromExcepition(e),
                exceptionMapper.getRootCauseMessage(e), new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
