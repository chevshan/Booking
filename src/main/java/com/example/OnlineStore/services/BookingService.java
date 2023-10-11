package com.example.OnlineStore.services;

import com.example.OnlineStore.models.Booking;
import com.example.OnlineStore.models.Person;
import com.example.OnlineStore.repositories.BookingRepository;
import com.example.OnlineStore.repositories.PersonRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PersonRepository personRepository;
    private final PersonService personService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, PersonRepository personRepository, PersonService personService) {
        this.bookingRepository = bookingRepository;
        this.personRepository = personRepository;
        this.personService = personService;
    }

    public Booking getBookingByOrderName(String orderName) {
        Optional<Booking> booking = bookingRepository.findByOrderName(orderName);
        return booking.orElse(null);
    }

    public List<Booking> getBookingsByPersonId(int id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBookings());
            return new ArrayList<>(person.get().getBookings());
        }
        else return Collections.emptyList();
    }

    @Transactional
    public void save(Booking booking, int id) {
        enrichBooking(booking, id);
        bookingRepository.save(booking);
    }

    private void enrichBooking (Booking booking, int id) {
        booking.setPerson(personService.findById(id));
        booking.setOrderTime(LocalDateTime.now());
    }

    @Transactional
    public void delete(String orderName) {
        Booking booking = bookingRepository.findByOrderName(orderName).orElse(null);
        assert booking != null;
        bookingRepository.delete(booking);
    }
}
