package com.tickets.ticketsv2.service;

import com.tickets.ticketsv2.exception.TrafficOffenceNotFoundException;
import com.tickets.ticketsv2.model.TrafficOffence;
import com.tickets.ticketsv2.repository.TrafficOffenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrafficOffenseService {

    private final TrafficOffenceRepository trafficOffenceRepository;

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


}
