package com.tickets.ticketsv2.controller;

import com.tickets.ticketsv2.command.CreateTicketCommand;
import com.tickets.ticketsv2.dto.TicketDto;
import com.tickets.ticketsv2.model.Ticket;
import com.tickets.ticketsv2.model.TrafficOffence;
import com.tickets.ticketsv2.service.TicketService;
import com.tickets.ticketsv2.service.TrafficOffenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private TrafficOffenseService trafficOffenseService;

    @GetMapping("/{id}")
    public TicketDto getTicketById(@PathVariable Long id) {
        return TicketDto.fromEntity(ticketService.getTicketById(id));
    }

    @GetMapping
    public Set<TicketDto> getAllTickets() {
        return ticketService.getAllTickets().stream()
                .map(TicketDto::fromEntity).collect(Collectors.toSet());
    }

    @PostMapping
    public TicketDto createTicket(CreateTicketCommand createTicketCommand) {
        Ticket ticket = new Ticket();
        ticket.setPesel(createTicketCommand.getPesel());
        ticket.setLocalDate(createTicketCommand.getLocalDate());
        Set<TrafficOffence> trafficOffences = createTicketCommand
                .getTrafficOffensesId().stream().map(c -> trafficOffenseService.findById(c)).collect(Collectors.toSet());
        ticket.setTrafficOffenceSet(trafficOffences);
        return TicketDto.fromEntity(ticketService.save(ticket));
    }


}
