package com.tickets.ticketsv2.service;

import com.tickets.ticketsv2.command.CreateTrafficOffenceCommand;
import com.tickets.ticketsv2.exception.TrafficOffenceNotFoundException;
import com.tickets.ticketsv2.model.TrafficOffence;
import com.tickets.ticketsv2.repository.TrafficOffenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrafficOffenceService {

    private final TrafficOffenceRepository trafficOffenceRepository;

    public TrafficOffence mapCommandToTrafficOffence(CreateTrafficOffenceCommand createTrafficOffenseCommand) {
        TrafficOffence trafficOffence = new TrafficOffence();
        trafficOffence.setNumberOfPoints(createTrafficOffenseCommand.getNumberOfPoints());
        trafficOffence.setTicketValue(createTrafficOffenseCommand.getTicketValue());
        trafficOffence.setOffenceType(createTrafficOffenseCommand.getOffenceType());
        return trafficOffence;
    }

    public TrafficOffence save(TrafficOffence trafficOffence) {
        return trafficOffenceRepository.save(trafficOffence);
    }

    public void delete(Long id) {
        trafficOffenceRepository.deleteById(id);
    }

    public TrafficOffence findById(Long id) {
        return trafficOffenceRepository.findById(id).orElseThrow(() -> new TrafficOffenceNotFoundException("TrafficOffence with id " + id + " not found!", id));
    }

    public List<TrafficOffence> findAll() {
        return trafficOffenceRepository.findAll();
    }

    public Set<TrafficOffence> mapIdToEntity(Set<Long> id) {
        return id.stream()
                .map(this::findById)
                .collect(Collectors.toSet());
    }


}
