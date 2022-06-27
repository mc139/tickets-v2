package com.tickets.ticketsv2.command;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class CreateTrafficOffenceCommand {

    @Max(value = 15)
    @Min(value = 0)
    @NonNull
    private int numberOfPoints;
    @NonNull
    private int ticketValue;
    @NonNull
    private String offenceType;


}
