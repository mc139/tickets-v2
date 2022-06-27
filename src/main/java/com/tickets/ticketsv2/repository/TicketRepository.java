package com.tickets.ticketsv2.repository;

import com.tickets.ticketsv2.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findAllByPesel(String pesel);
}
