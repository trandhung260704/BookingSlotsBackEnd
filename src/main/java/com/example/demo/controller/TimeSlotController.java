package com.example.demo.controller;

import com.example.demo.service.TimeSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/timeslots")
@CrossOrigin(origins = "http://localhost:3002", allowCredentials = "true")
@RequiredArgsConstructor
public class TimeSlotController {
    private final TimeSlotService timeSlotService;

    @GetMapping("/price")
    public ResponseEntity<BigDecimal> getPrice(
            @RequestParam("pitchId") Integer pitchId,
            @RequestParam("date") LocalDate date,
            @RequestParam("startTime") LocalTime startTime,
            @RequestParam("endTime") LocalTime endTime
    ) {
        BigDecimal price = timeSlotService.getPrice(pitchId, date, startTime, endTime);
        return ResponseEntity.ok(price);
    }
}