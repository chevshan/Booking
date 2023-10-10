package com.example.OnlineStore.util;

import com.example.OnlineStore.Facades.PersonFacade;
import com.example.OnlineStore.dto.PersonDTO;
import com.example.OnlineStore.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonFacade personFacade;

    @Autowired
    public PersonValidator(PersonFacade personFacade) {
        this.personFacade = personFacade;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PersonDTO person = (PersonDTO) o;
        if (personFacade.findPerson(person.getUsername()) != null) {
            errors.rejectValue("username", "There is already a person with this name");
        }
    }
}
