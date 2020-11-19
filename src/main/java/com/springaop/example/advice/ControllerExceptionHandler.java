package com.springaop.example.advice;

import com.springaop.example.model.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException runtimeException) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .exception(runtimeException.getClass().getName())
                .timestamp(System.currentTimeMillis())
                .error(runtimeException.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }
}
