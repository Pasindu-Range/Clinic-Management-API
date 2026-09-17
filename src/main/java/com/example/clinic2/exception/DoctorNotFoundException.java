package com.example.clinic2.exception;

public class DoctorNotFoundException extends RuntimeException {

    public DoctorNotFoundException(String message) {
        super(message);
    }
}
