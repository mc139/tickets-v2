package com.tickets.ticketsv2.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException(Long id) {
        log.error("Could not find ticket with id: " + id);
    }
}
