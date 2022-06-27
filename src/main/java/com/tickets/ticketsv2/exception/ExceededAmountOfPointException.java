package com.tickets.ticketsv2.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceededAmountOfPointException extends RuntimeException {

    public ExceededAmountOfPointException(String message) {
        super(message);
        log.info("Amount of points has been exceeded!");
    }
}
