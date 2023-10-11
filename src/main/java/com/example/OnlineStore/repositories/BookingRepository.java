package com.example.OnlineStore.repositories;

import com.example.OnlineStore.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Optional<Booking> findByOrderName(String orderName);
    List<Booking> findByPersonId(int id);
}
