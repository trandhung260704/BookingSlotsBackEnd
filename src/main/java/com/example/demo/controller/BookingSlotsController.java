package com.example.demo.controller;

import com.example.demo.entity.BookingSlots;
import com.example.demo.repository.BookingSlotsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookingslots")
@CrossOrigin(origins = "http://localhost:3002", allowCredentials = "true")
@RequiredArgsConstructor
public class BookingSlotsController {
    private final BookingSlotsRepository bookingSlotsRepository;

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping
    public List<BookingSlots> getSlotsByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return bookingSlotsRepository.findByDate(date);
    }
}