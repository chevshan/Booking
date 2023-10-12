package com.example.OnlineStore.serviceInterface;

import com.example.OnlineStore.models.Person;

import java.util.List;

public interface PersonService {
    Person findByUsername(String username);
    Person findByPasswordAndUsername(String password, String username);
    List<Person> findAll();
    Person findById(int id);
}
