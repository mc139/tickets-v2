package com.tickets.ticketsv2.command;

import lombok.*;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class CreateTicketCommand {

    @PESEL
    @NonNull
    private String pesel;

    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @PastOrPresent
    private LocalDate localDate;

    private int totalTicketPrice;

    private Set<Long> trafficOffensesId = new HashSet<>();

}
