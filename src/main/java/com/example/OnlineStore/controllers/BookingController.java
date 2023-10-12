package com.example.OnlineStore.controllers;

import com.example.OnlineStore.Facades.BookingFacadeImpl;
import com.example.OnlineStore.dto.BookingDTO;
import com.example.OnlineStore.util.BookingDeleteException;
import com.example.OnlineStore.util.ErrorResponse;
import com.example.OnlineStore.util.BookingNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking/{orderName}")
public class BookingController {
    private final BookingFacadeImpl bookingFacadeImpl;

    public BookingController(BookingFacadeImpl bookingFacadeImpl) {
        this.bookingFacadeImpl = bookingFacadeImpl;
    }

    @GetMapping()
    public ResponseEntity<BookingDTO> getBookingByOrderName(@PathVariable String orderName) {
        BookingDTO bookingDTO = bookingFacadeImpl.findBooking(orderName);
        return ResponseEntity.ok(bookingDTO);
    }

    @PostMapping("/delete")
    public ResponseEntity<HttpStatus> deleteBooking(@PathVariable String orderName) {
        bookingFacadeImpl.deleteBooking(orderName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(BookingNotFoundException exception) {
        ErrorResponse response = new ErrorResponse(
                "Person with this username wasn't found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(BookingDeleteException exception) {
        ErrorResponse response = new ErrorResponse(
                "Book with this username wasn't found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
