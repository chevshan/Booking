package com.example.OnlineStore.controllers;

import com.example.OnlineStore.Facades.PersonFacadeImpl;
import com.example.OnlineStore.dto.PersonDTO;
import com.example.OnlineStore.util.ErrorResponse;
import com.example.OnlineStore.util.IncorrectDataException;
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

    private final PersonFacadeImpl personFacadeImpl;

    public AuthController(PersonValidator personValidator, PersonFacadeImpl personFacadeImpl) {
        this.personValidator = personValidator;
        this.personFacadeImpl = personFacadeImpl;
    }

    @GetMapping("/registration")
    public ResponseEntity<?> performRegistration(@RequestBody @Valid PersonDTO personDTO,
                                                          BindingResult bindingResult) {
        personValidator.validate(personDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(HttpStatus.NOT_ACCEPTABLE);
        }

        personFacadeImpl.registerPerson(personDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<PersonDTO> login(@RequestBody @Valid PersonDTO personDTO) {

        PersonDTO foundedPerson = personFacadeImpl.getPersonByPasswordAndUsername
                (personDTO.getPassword(),personDTO.getUsername());
        return ResponseEntity.ok(foundedPerson);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(PersonNotFoundException exception) {
        ErrorResponse response = new ErrorResponse(
                "There is no such person!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(IncorrectDataException exception) {
        ErrorResponse response = new ErrorResponse(
                "Login or password is not correct!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
