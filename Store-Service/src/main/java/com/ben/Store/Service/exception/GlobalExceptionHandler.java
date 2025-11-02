package com.ben.Store.Service.exception;

import com.ben.Store.Service.model.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StoreException.class)
    public ResponseEntity<ExceptionResponse> StoreExceptionHandler(
            StoreException storeException, WebRequest request) {

        ExceptionResponse response = new ExceptionResponse(
                storeException.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }
}
