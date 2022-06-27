package com.tickets.ticketsv2.dto;

import com.tickets.ticketsv2.model.Person;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import java.io.Serializable;

@Data
@Builder
public class PersonDto implements Serializable {
    private final Long id;
    private final String pesel;
    @Length(min = 3, max = 20)
    private final String name;
    @Length(min = 3, max = 20)
    private final String surname;
    @Email
    private final String email;

    public static PersonDto fromEntity(Person person){
        return PersonDto.builder()
                .id(person.getId())
                .pesel(person.getPesel())
                .name(person.getName())
                .surname(person.getSurname())
                .email(person.getEmail())
                .build();
    }
}
