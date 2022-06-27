package com.tickets.ticketsv2.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrafficOffenceNotFoundException extends RuntimeException {
    public TrafficOffenceNotFoundException(String message, long id) {
        super(message);
        log.info("Could not find a traffic offence with id : " + id);
    }
}
