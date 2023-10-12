package com.example.OnlineStore.facadeInterface;

import com.example.OnlineStore.dto.BookingDTO;

import java.util.List;

public interface BookingFacade {
    List<BookingDTO> getBookingsByPersonId(int id);
    BookingDTO findBooking(String orderName);
    void saveBooking(BookingDTO bookingDTO, int id);
    void deleteBooking(String orderName);
}
