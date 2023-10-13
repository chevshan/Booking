package com.example.OnlineStore.util;

import com.example.OnlineStore.dto.BookingDTO;
import com.example.OnlineStore.services.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Configuration
public class BookingValidator implements Validator {

    private final BookingServiceImpl bookingService;

    @Autowired
    public BookingValidator(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return BookingDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BookingDTO booking = (BookingDTO) o;
        if (bookingService.getBookingByOrderName(booking.getOrderName()) != null) {
            errors.rejectValue("orderName", "There is already a booking with this name");
        }
    }
}
