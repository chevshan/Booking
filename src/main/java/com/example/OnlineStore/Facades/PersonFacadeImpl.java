package com.example.OnlineStore.Facades;

import com.example.OnlineStore.dto.PersonDTO;
import com.example.OnlineStore.facadeInterface.PersonFacade;
import com.example.OnlineStore.services.PersonServiceImpl;
import com.example.OnlineStore.services.RegistrationServiceImpl;
import com.example.OnlineStore.util.Mapper;
import com.example.OnlineStore.util.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonFacadeImpl implements PersonFacade {
    private final PersonServiceImpl personServiceImpl;
    private final RegistrationServiceImpl registrationServiceImpl;
    private final Mapper mapper;

    @Autowired
    public PersonFacadeImpl(PersonServiceImpl personServiceImpl, RegistrationServiceImpl registrationServiceImpl, Mapper mapper) {
        this.personServiceImpl = personServiceImpl;
        this.registrationServiceImpl = registrationServiceImpl;
        this.mapper = mapper;
    }

    public PersonDTO findPerson(String username) {
        if (personServiceImpl.findByUsername(username) == null) {
            return null;
        }
        return mapper.convertToPersonDTO(personServiceImpl.findByUsername(username));
    }

    public PersonDTO getPersonByPasswordAndUsername(String password, String username) {
        if (personServiceImpl.findByPasswordAndUsername(password, username) == null) {
            throw new PersonNotFoundException();
        }
        return mapper.convertToPersonDTO(personServiceImpl.findByPasswordAndUsername(password, username));
    }

    public void registerPerson(PersonDTO personDTO) {
        registrationServiceImpl.register(mapper.convertToPerson(personDTO));
    }

    public List<PersonDTO> findAll() {
        return personServiceImpl.findAll().stream().map(mapper::convertToPersonDTO)
                .collect(Collectors.toList());
    }

}
