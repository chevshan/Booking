package com.example.OnlineStore.util;

import com.example.OnlineStore.dto.BookingDTO;
import com.example.OnlineStore.dto.PersonDTO;
import com.example.OnlineStore.models.Booking;
import com.example.OnlineStore.models.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {
    private final ModelMapper modelMapper;

    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    public PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    public Booking convertToBooking(BookingDTO bookingDTO) {
        return modelMapper.map(bookingDTO, Booking.class);
    }

    public BookingDTO convertToBookingDTO(Booking booking) {
        return modelMapper.map(booking, BookingDTO.class);
    }
}
