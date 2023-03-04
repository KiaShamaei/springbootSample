package com.example.springbootsample.exception.handler;


import com.example.springbootsample.exception.message.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorControllerHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> all(Exception e){
        var error = ErrorResponse.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .time(LocalDateTime.now()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> validateEntity(MethodArgumentNotValidException e){
        List<String> errors = e.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        var error = ErrorResponse.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .errors(getErrorsMap(errors))
                .message(errors.get(0))
                .time(LocalDateTime.now()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
