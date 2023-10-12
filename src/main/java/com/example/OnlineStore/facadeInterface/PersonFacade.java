package com.example.OnlineStore.facadeInterface;

import com.example.OnlineStore.dto.PersonDTO;

import java.util.List;

public interface PersonFacade {
    PersonDTO findPerson(String username);
    PersonDTO getPersonByPasswordAndUsername(String password, String username);
    void registerPerson(PersonDTO personDTO);
    List<PersonDTO> findAll();
}
