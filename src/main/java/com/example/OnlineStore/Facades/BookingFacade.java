package com.example.OnlineStore.Facades;

import com.example.OnlineStore.dto.BookingDTO;
import com.example.OnlineStore.services.BookingService;
import com.example.OnlineStore.util.BookingNotFoundException;
import com.example.OnlineStore.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingFacade {
    private final BookingService bookingService;
    private final Mapper mapper;

    @Autowired
    public BookingFacade(BookingService bookingService, Mapper mapper) {
        this.bookingService = bookingService;
        this.mapper = mapper;
    }

    public List<BookingDTO> getBookingsByPersonId(int id) {
        return bookingService.getBookingsByPersonId(id).stream().map(mapper::convertToBookingDTO)
                .collect(Collectors.toList());
    }

    public BookingDTO findBooking(String orderName) {
        if (bookingService.getBookingByOrderName(orderName) == null) {
            throw new BookingNotFoundException();
        }
        else {
            return mapper.convertToBookingDTO(bookingService.getBookingByOrderName(orderName));
        }
    }

    public void saveBooking(BookingDTO bookingDTO, int id) {
        bookingService.save(mapper.convertToBooking(bookingDTO), id);
    }

    public void deleteBooking(String orderName) {
        bookingService.delete(orderName);
    }
}
