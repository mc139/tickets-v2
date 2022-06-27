package com.tickets.ticketsv2.service;

import com.tickets.ticketsv2.config.TicketProperties;
import com.tickets.ticketsv2.exception.ExceededAmountOfPointException;
import com.tickets.ticketsv2.exception.PeselNotFoundException;
import com.tickets.ticketsv2.exception.TicketNotFoundException;
import com.tickets.ticketsv2.model.Person;
import com.tickets.ticketsv2.model.Ticket;
import com.tickets.ticketsv2.model.TrafficOffence;
import com.tickets.ticketsv2.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketService {

    private final TicketRepository ticketRepository;
    private final PersonService personService;
    private final MailService mailService;
    private final TicketProperties ticketProperties;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getAllTickets(@PESEL String pesel) {
        return ticketRepository.findAllByPesel(pesel);
    }

    public int getTotalNumberOfPersonPoints(String pesel) {
        return getAllTickets(pesel).stream()
                .mapToInt(ticket -> ticket.getTrafficOffenceList()
                        .stream()
                        .mapToInt(TrafficOffence::getNumberOfPoints).sum())
                .sum();
    }

    public int getTotalNumberOfPointsFromTicket(Ticket ticket) {
        return ticket.getTrafficOffenceList().stream()
                .mapToInt(TrafficOffence::getNumberOfPoints)
                .sum();
    }

    public int getTotalTicketPrice(Ticket ticket) {
        Set<TrafficOffence> trafficOffenceList = ticket.getTrafficOffenceList();
        return trafficOffenceList.stream().mapToInt(TrafficOffence::getTicketValue).sum();
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException(id));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Ticket save(Ticket ticket) {
        if (getTotalNumberOfPersonPoints(ticket.getPesel()) >= ticketProperties.getMaxPointsPerYear()){
            throw new ExceededAmountOfPointException("Person has already lost a driving licence cannot make another ticket!");
        }
        if (!personService.isPeselInDataBase(ticket.getPesel())) {
            throw new PeselNotFoundException(ticket.getPesel());
        }
        ticket.setTotalTicketPrice(getTotalTicketPrice(ticket));
        Person person = personService.findPersonByPesel(ticket.getPesel());

        if (countPointsFromCurrentYear(ticket.getPesel()) + getTotalNumberOfPointsFromTicket(ticket) > ticketProperties.getMaxPointsPerYear()) {
            //todo emailService implementation cant be done yet due to new google policy
//            mailService.sendEmail(person.getEmail(), ticketProperties.getSubject(), ticketProperties.getText());
            log.info("Email has been sent to : " + person.getEmail());
        }
        return ticketRepository.save(ticket);
    }

    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }

    public int countPointsFromCurrentYear(String pesel) {
        return ticketRepository.findAllByPesel(pesel).stream()
                .filter(Objects::nonNull)
                .filter(c -> c.getLocalDate().isAfter(LocalDate.now().minusYears(1)))
                .mapToInt(c -> c.getTrafficOffenceList()
                        .stream()
                        .filter(Objects::nonNull)
                        .mapToInt(TrafficOffence::getNumberOfPoints)
                        .sum())
                .sum();
    }
}
