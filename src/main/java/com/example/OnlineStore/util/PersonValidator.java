package com.example.OnlineStore.util;

import com.example.OnlineStore.Facades.PersonFacadeImpl;
import com.example.OnlineStore.dto.PersonDTO;
import com.example.OnlineStore.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonFacadeImpl personFacadeImpl;

    @Autowired
    public PersonValidator(PersonFacadeImpl personFacadeImpl) {
        this.personFacadeImpl = personFacadeImpl;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PersonDTO person = (PersonDTO) o;
        if (personFacadeImpl.findPerson(person.getUsername()) != null) {
            errors.rejectValue("username", "There is already a person with this name");
        }
    }
}
