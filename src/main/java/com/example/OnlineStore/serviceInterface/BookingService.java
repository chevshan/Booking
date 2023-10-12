package com.example.OnlineStore.serviceInterface;


import com.example.OnlineStore.models.Booking;

import java.util.List;

public interface BookingService {
    Booking getBookingByOrderName(String orderName);
    List<Booking> getBookingsByPersonId(int id);
    void save(Booking booking, int id);
    void enrichBooking (Booking booking, int id);
    void delete(String orderName);

}
