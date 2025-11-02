package com.ben.Branch.Service.exception;

import com.ben.Branch.Service.model.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends Exception{

    @ExceptionHandler(BranchException.class)
    public ResponseEntity<ExceptionResponse> handleException(BranchException branchException
    , WebRequest request) {

        ExceptionResponse response = new ExceptionResponse(
                branchException.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
