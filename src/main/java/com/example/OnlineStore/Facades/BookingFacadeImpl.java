package com.example.OnlineStore.Facades;

import com.example.OnlineStore.dto.BookingDTO;
import com.example.OnlineStore.facadeInterface.BookingFacade;
import com.example.OnlineStore.services.BookingServiceImpl;
import com.example.OnlineStore.util.BookingNotFoundException;
import com.example.OnlineStore.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingFacadeImpl implements BookingFacade {
    private final BookingServiceImpl bookingServiceImpl;
    private final Mapper mapper;

    @Autowired
    public BookingFacadeImpl(BookingServiceImpl bookingServiceImpl, Mapper mapper) {
        this.bookingServiceImpl = bookingServiceImpl;
        this.mapper = mapper;
    }

    public List<BookingDTO> getBookingsByPersonId(int id) {
        return bookingServiceImpl.getBookingsByPersonId(id).stream().map(mapper::convertToBookingDTO)
                .collect(Collectors.toList());
    }

    public BookingDTO findBooking(String orderName) {
        if (bookingServiceImpl.getBookingByOrderName(orderName) == null) {
            throw new BookingNotFoundException();
        }
        else {
            return mapper.convertToBookingDTO(bookingServiceImpl.getBookingByOrderName(orderName));
        }
    }

    public void saveBooking(BookingDTO bookingDTO, int id) {
        bookingServiceImpl.save(mapper.convertToBooking(bookingDTO), id);
    }

    public void deleteBooking(String orderName) {
        bookingServiceImpl.delete(orderName);
    }
}
