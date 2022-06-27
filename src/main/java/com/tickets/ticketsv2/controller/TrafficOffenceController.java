package com.tickets.ticketsv2.controller;

import com.tickets.ticketsv2.command.CreateTrafficOffenceCommand;
import com.tickets.ticketsv2.dto.TrafficOffenceDto;
import com.tickets.ticketsv2.model.TrafficOffence;
import com.tickets.ticketsv2.service.TrafficOffenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/traffic-offences")
public class TrafficOffenceController {

    private final TrafficOffenceService trafficOffenceService;

    @GetMapping("/{id}")
    public TrafficOffenceDto getTrafficOffenceById(@PathVariable Long id) {
        return TrafficOffenceDto.fromEntity(trafficOffenceService.findById(id));
    }

    @GetMapping
    public Set<TrafficOffenceDto> getAllTrafficOffences() {
        return trafficOffenceService.findAll().stream()
                .map(TrafficOffenceDto::fromEntity)
                .collect(Collectors.toSet());
    }

    @PostMapping
    public TrafficOffenceDto createTicket(CreateTrafficOffenceCommand createTrafficOffenseCommand) {
        TrafficOffence trafficOffence = trafficOffenceService.mapCommandToTrafficOffence(createTrafficOffenseCommand);
        return TrafficOffenceDto.fromEntity(trafficOffenceService.save(trafficOffence));
    }

    @DeleteMapping("/{id}")
    public void deleteTrafficOffence(@PathVariable Long id) {
        trafficOffenceService.delete(id);
    }
}
