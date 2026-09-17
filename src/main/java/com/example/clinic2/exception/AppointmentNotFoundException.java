package com.example.clinic2.exception;

public class AppointmentNotFoundException extends RuntimeException {

    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
