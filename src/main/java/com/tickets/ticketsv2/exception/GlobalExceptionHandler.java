package com.tickets.ticketsv2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public List<String> handleBindException(BindException exception) {
        List<String> errors = new ArrayList<>();
        exception.getFieldErrors().forEach(err -> errors.add(err.getDefaultMessage()));
        return errors;
    }

    @ExceptionHandler(ExceededAmountOfPointException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String ExceededAmountOfPointHandler(ExceededAmountOfPointException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler({
            PersonNotFoundException.class,
            PeselNotFoundException.class,
            TicketNotFoundException.class,
            TrafficOffenceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String PersonNotFoundHandler(PersonNotFoundException exception) {
        return exception.getMessage();
    }

}
