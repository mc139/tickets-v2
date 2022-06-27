package com.tickets.ticketsv2.controller;

import com.tickets.ticketsv2.command.CreateTicketCommand;
import com.tickets.ticketsv2.dto.TicketDto;
import com.tickets.ticketsv2.model.Ticket;
import com.tickets.ticketsv2.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/{id}")
    public TicketDto getTicketById(@PathVariable Long id) {
        return TicketDto.fromEntity(ticketService.getTicketById(id));
    }

    @GetMapping
    public Set<TicketDto> getAllTickets() {
        return ticketService.getAllTickets().stream()
                .map(TicketDto::fromEntity)
                .collect(Collectors.toSet());
    }

    @PostMapping
    public TicketDto createTicket(CreateTicketCommand createTicketCommand) {
        Ticket ticket = ticketService.mapCommandToTicket(createTicketCommand);
        return TicketDto.fromEntity(ticketService.save(ticket));
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteById(id);
    }

}
