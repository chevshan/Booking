package com.example.OnlineStore.repositories;

import com.example.OnlineStore.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);
    Optional<Person> findByPasswordAndUsername(String password, String username);
}
