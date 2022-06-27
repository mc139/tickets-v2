package com.tickets.ticketsv2.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TrafficOffence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Max(value = 15)
    @Min(value = 0)
    @NonNull
    private int numberOfPoints;
    @NonNull
    private int ticketValue;
    @NonNull
    private String offenceType;
}
