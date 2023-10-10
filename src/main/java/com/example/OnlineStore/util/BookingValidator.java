package com.example.OnlineStore.util;

import com.example.OnlineStore.Facades.BookingFacade;
import com.example.OnlineStore.dto.BookingDTO;
import com.example.OnlineStore.models.Booking;
import com.example.OnlineStore.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Configuration
public class BookingValidator implements Validator {

    private final BookingFacade bookingFacade;

    @Autowired
    public BookingValidator(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return BookingDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BookingDTO booking = (BookingDTO) o;
        if (bookingFacade.findBooking(booking.getOrderName()) != null) {
            errors.rejectValue("orderName", "There is already a booking with this name");
        }
    }
}
