package com.example.clinic2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> RuntimeExceptionHandler(RuntimeException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<String> DoctorNotFoundHandler(DoctorNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<String> PatientNotFoundHandler(PatientNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<String> AppointmentNotFoundHandler(AppointmentNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String MethodArgumentNotValidHandler(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();
    }

}
