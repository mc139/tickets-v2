package com.tickets.ticketsv2.model;

import lombok.*;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.HashSet;
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
    private LocalDate ticket_date;

    private int totalTicketPrice;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ticket_id")
    private Set<TrafficOffence> trafficOffenceSet = new HashSet<>();

}
