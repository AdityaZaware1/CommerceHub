package com.ben.Access.Service.exception;

import com.ben.Access.Service.model.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    private ResponseEntity<ExceptionResponse> UserExceptionHandler(
            UserException userException, WebRequest webRequest) {

        ExceptionResponse response = new ExceptionResponse(
                userException.getMessage(),
                webRequest.getDescription(false),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
