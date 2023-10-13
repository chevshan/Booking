package com.example.OnlineStore.Facades;

import com.example.OnlineStore.dto.PersonDTO;
import com.example.OnlineStore.facadeInterface.PersonFacade;
import com.example.OnlineStore.services.PersonServiceImpl;
import com.example.OnlineStore.services.RegistrationServiceImpl;
import com.example.OnlineStore.util.IncorrectDataException;
import com.example.OnlineStore.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

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

    public PersonDTO getPersonByPasswordAndUsername(String password, String username){
        try {
            registrationServiceImpl.authenticateUser(password, username);
        }
        catch (AuthenticationException exception) {
            throw new IncorrectDataException();
        }
        return mapper.convertToPersonDTO(personServiceImpl.findByUsername(username));
    }

    public void registerPerson(PersonDTO personDTO) {
        registrationServiceImpl.register(mapper.convertToPerson(personDTO));
    }

}
