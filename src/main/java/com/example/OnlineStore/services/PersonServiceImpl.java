package com.example.OnlineStore.services;

import com.example.OnlineStore.models.Person;
import com.example.OnlineStore.repositories.PersonRepository;
import com.example.OnlineStore.serviceInterface.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findByUsername(String username) {
        Optional<Person> fondedPerson = personRepository.findByUsername(username);
        return fondedPerson.orElse(null);
    }

    public Person findByPasswordAndUsername(String password, String username) {
        Optional<Person> foundedPerson = personRepository.findByPasswordAndUsername(password, username);
        return foundedPerson.orElse(null);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(int id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElse(null);
    }
}