package com.example.demo.service;

import com.example.demo.entity.TimeSlots;
import com.example.demo.repository.TimeSlotsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimeSlotService {
    private final TimeSlotsRepository timeSlotsRepository;

    public BigDecimal getPrice(Integer pitchId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        Optional<TimeSlots> slot = timeSlotsRepository.findPriceForSlot(pitchId, dayOfWeek, startTime, endTime);
        return slot.map(TimeSlots::getPrice).orElse(BigDecimal.ZERO);
    }
}