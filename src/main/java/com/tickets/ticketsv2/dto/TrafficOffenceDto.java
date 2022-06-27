package com.tickets.ticketsv2.dto;

import com.tickets.ticketsv2.model.Person;
import com.tickets.ticketsv2.model.TrafficOffence;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@Builder
public class TrafficOffenceDto implements Serializable {
    private final Long id;
    @Max(value = 15)
    @Min(value = 0)
    private final int numberOfPoints;
    private final int ticketValue;
    private final String offenceType;

    public static TrafficOffenceDto fromEntity(TrafficOffence trafficOffence){
        return TrafficOffenceDto.builder()
                .id(trafficOffence.getId())
                .numberOfPoints(trafficOffence.getTicketValue())
                .offenceType(trafficOffence.getOffenceType())
                .build();
    }
}
