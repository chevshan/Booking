package com.example.OnlineStore.controllers;

import com.example.OnlineStore.Facades.BookingFacade;
import com.example.OnlineStore.dto.BookingDTO;
import com.example.OnlineStore.util.BookingValidator;
import com.example.OnlineStore.util.ErrorResponse;
import com.example.OnlineStore.util.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person/{id}")
public class PersonController {

    private final BookingFacade bookingFacade;
    private final BookingValidator bookingValidator;

    @Autowired
    public PersonController(BookingFacade bookingFacade, BookingValidator bookingValidator) {
        this.bookingFacade = bookingFacade;
        this.bookingValidator = bookingValidator;
    }

    @GetMapping("/bookings")
    public List<BookingDTO> getBookings(@PathVariable int id) {
        return bookingFacade.getBookingsByPersonId(id);
    }

    @PostMapping("/addBooking")
    public ResponseEntity<HttpStatus> addBooking(@RequestBody @Valid BookingDTO bookingDTO,
                                                 @PathVariable int id, BindingResult bindingResult) {
        bookingValidator.validate(bookingDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(HttpStatus.NOT_ACCEPTABLE);
        }
        bookingFacade.saveBooking(bookingDTO, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(PersonNotFoundException exception) {
        ErrorResponse response = new ErrorResponse(
                "There is no such person!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}