package com.example.dgpayproject.exceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CostumeExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleException(Exception exception) {

        if (exception instanceof IllegalArgumentException) {
            return ErrorResponse.of(-1, exception.getMessage());
        }
        return ErrorResponse.of(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(GlobalException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleException(GlobalException exception) {

        return ErrorResponse.of(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}





