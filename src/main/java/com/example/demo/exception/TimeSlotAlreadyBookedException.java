package com.example.demo.exception;

public class TimeSlotAlreadyBookedException extends RuntimeException {
    public TimeSlotAlreadyBookedException(String message) {
        super(message);
    }
}