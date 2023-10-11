package com.example.OnlineStore.Facades;

import com.example.OnlineStore.dto.PersonDTO;
import com.example.OnlineStore.services.PersonService;
import com.example.OnlineStore.services.RegistrationService;
import com.example.OnlineStore.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonFacade {
    private final PersonService personService;
    private final RegistrationService registrationService;
    private final Mapper mapper;

    @Autowired
    public PersonFacade(PersonService personService, RegistrationService registrationService, Mapper mapper) {
        this.personService = personService;
        this.registrationService = registrationService;
        this.mapper = mapper;
    }

    public PersonDTO findPerson(String username) {
        if (personService.findByUsername(username) == null) {
            return null;
        }
        return mapper.convertToPersonDTO(personService.findByUsername(username));
    }

    public PersonDTO getPersonByPasswordAndUsername(String password, String username) {
        if (personService.findByPasswordAndUsername(password, username) == null) {
            return null;
        }
        return mapper.convertToPersonDTO(personService.findByPasswordAndUsername(password, username));
    }

    public void registerPerson(PersonDTO personDTO) {
        registrationService.register(mapper.convertToPerson(personDTO));
    }

    public List<PersonDTO> findAll() {
        return personService.findAll().stream().map(mapper::convertToPersonDTO)
                .collect(Collectors.toList());
    }

}
