package com.tickets.ticketsv2.repository;

import com.tickets.ticketsv2.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByPesel(String pesel);

}
