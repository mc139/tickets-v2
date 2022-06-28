package com.tickets.ticketsv2.service;

import com.tickets.ticketsv2.command.CreatePersonCommand;
import com.tickets.ticketsv2.dto.PersonDto;
import com.tickets.ticketsv2.exception.PersonNotFoundException;
import com.tickets.ticketsv2.model.Person;
import com.tickets.ticketsv2.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person findPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Person dazynt egzist"));
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public Person findPersonByPesel(String pesel) {
        return personRepository.findByPesel(pesel)
                .orElseThrow(() -> new PersonNotFoundException("Person with pesel " + pesel + " doesn't exists!"));
    }

    public boolean isPeselInDataBase(String pesel) {
        return personRepository.findByPesel(pesel).isPresent();
    }

    public Person updatePerson(CreatePersonCommand createPersonCommand, Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Could not find a person with id : " + id));

        person.setName(createPersonCommand.getName());
        person.setSurname(createPersonCommand.getSurname());
        person.setEmail(createPersonCommand.getEmail());
        person.setPesel(createPersonCommand.getPesel());

        return personRepository.save(person);
    }
}
