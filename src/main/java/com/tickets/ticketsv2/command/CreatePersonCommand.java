package com.tickets.ticketsv2.command;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.Email;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePersonCommand {

    @PESEL
    private String pesel;
    @Length(min = 3, max = 20)
    private String name;
    @Length(min = 3, max = 20)
    private String surname;
    @Email
    private String email;

}
