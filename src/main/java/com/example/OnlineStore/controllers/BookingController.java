package com.example.OnlineStore.controllers;

import com.example.OnlineStore.Facades.BookingFacade;
import com.example.OnlineStore.dto.BookingDTO;
import com.example.OnlineStore.util.BookingErrorResponse;
import com.example.OnlineStore.util.BookingNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking/{orderName}")
public class BookingController {
    private final BookingFacade bookingFacade;

    public BookingController(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @GetMapping()
    public ResponseEntity<BookingDTO> getBookingByOrderName(@PathVariable String orderName) {
        BookingDTO bookingDTO = bookingFacade.findBooking(orderName);
        return ResponseEntity.ok(bookingDTO);
    }

    @PostMapping("/delete")
    public ResponseEntity<HttpStatus> deleteBooking(@PathVariable String orderName) {
        bookingFacade.deleteBooking(orderName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<BookingErrorResponse> handleException(BookingNotFoundException exception) {
        BookingErrorResponse response = new BookingErrorResponse(
                "Person with this username wasn't found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
