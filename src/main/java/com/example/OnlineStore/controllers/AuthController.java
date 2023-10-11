package com.example.OnlineStore.controllers;

import com.example.OnlineStore.Facades.PersonFacade;
import com.example.OnlineStore.dto.PersonDTO;
import com.example.OnlineStore.util.ErrorResponse;
import com.example.OnlineStore.util.PersonNotFoundException;
import com.example.OnlineStore.util.PersonValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;

    private final PersonFacade personFacade;

    public AuthController(PersonValidator personValidator, PersonFacade personFacade) {
        this.personValidator = personValidator;
        this.personFacade = personFacade;
    }

    @GetMapping("/registration")
    public ResponseEntity<?> performRegistration(@RequestBody @Valid PersonDTO personDTO,
                                                          BindingResult bindingResult) {
        personValidator.validate(personDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(HttpStatus.NOT_ACCEPTABLE);
        }

        personFacade.registerPerson(personDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid PersonDTO personDTO) {

        PersonDTO foundedPerson = personFacade.getPersonByPasswordAndUsername
                (personDTO.getPassword(),personDTO.getUsername());
        if (foundedPerson == null) {
            return ResponseEntity.ok("Incorrect data...");
        }
        return ResponseEntity.ok("Welcome to the login page!");
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
