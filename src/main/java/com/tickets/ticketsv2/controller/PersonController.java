package com.tickets.ticketsv2.controller;

import com.tickets.ticketsv2.command.CreatePersonCommand;
import com.tickets.ticketsv2.dto.PersonDto;
import com.tickets.ticketsv2.model.Person;
import com.tickets.ticketsv2.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    @GetMapping("/{id}")
    public PersonDto getPersonById(@PathVariable Long id) {
        return PersonDto.fromEntity(personService.findPersonById(id));
    }

    @GetMapping
    public Set<PersonDto> getAllPeople() {
        return personService.getAllPersons().stream()
                .map(PersonDto::fromEntity)
                .collect(Collectors.toSet());
    }

    @PostMapping()
    public PersonDto createPerson(CreatePersonCommand createPersonCommand) {
        Person person = new Person();
        person.setPesel(createPersonCommand.getPesel());
        person.setName(createPersonCommand.getName());
        person.setSurname(createPersonCommand.getSurname());
        person.setEmail(createPersonCommand.getEmail());
        return PersonDto.fromEntity(personService.save(person));
    }

    //todo to be checked by others
    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(CreatePersonCommand personCommand,@PathVariable Long id){
        Person person = personService.updatePerson(personCommand, id);

        return new ResponseEntity<>(PersonDto.fromEntity(person), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deletePersonByID(@PathVariable Long id) {
        personService.deletePerson(id);
    }

}
