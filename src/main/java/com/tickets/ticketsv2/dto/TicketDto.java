package com.tickets.ticketsv2.dto;

import com.tickets.ticketsv2.model.Ticket;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class TicketDto implements Serializable {
    private final Long id;
    private final String pesel;
    @PastOrPresent
    private final LocalDate localDate;
    private final int totalTicketPrice;
    private final Set<TrafficOffenceDto> trafficOffenceSet;

    public static TicketDto fromEntity(Ticket ticket) {
        return TicketDto.builder()
                .id(ticket.getId())
                .pesel(ticket.getPesel())
                .localDate(ticket.getLocalDate())
                .totalTicketPrice(ticket.getTotalTicketPrice())
                .trafficOffenceSet(ticket.getTrafficOffenceSet().stream()
                        .map(TrafficOffenceDto::fromEntity)
                        .collect(Collectors.toSet()))
                .build();
    }

}
