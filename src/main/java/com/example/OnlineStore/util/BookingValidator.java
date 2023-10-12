package com.example.OnlineStore.util;

import com.example.OnlineStore.Facades.BookingFacadeImpl;
import com.example.OnlineStore.dto.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Configuration
public class BookingValidator implements Validator {

    private final BookingFacadeImpl bookingFacadeImpl;

    @Autowired
    public BookingValidator(BookingFacadeImpl bookingFacadeImpl) {
        this.bookingFacadeImpl = bookingFacadeImpl;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return BookingDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BookingDTO booking = (BookingDTO) o;
        if (bookingFacadeImpl.findBooking(booking.getOrderName()) != null) {
            errors.rejectValue("orderName", "There is already a booking with this name");
        }
    }
}
