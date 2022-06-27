package com.tickets.ticketsv2.model;

import lombok.*;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PESEL
    @NonNull
    private String pesel;

    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @PastOrPresent
    private LocalDate localDate;

    private int totalTicketPrice;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private Set<TrafficOffence> trafficOffenceSet = new HashSet<>();
}
