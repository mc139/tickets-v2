package com.tickets.ticketsv2.repository;


import com.tickets.ticketsv2.model.TrafficOffence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrafficOffenceRepository extends JpaRepository<TrafficOffence, Long> {
}
