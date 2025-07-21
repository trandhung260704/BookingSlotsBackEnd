package com.example.demo.repository;

import com.example.demo.entity.TimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface TimeSlotsRepository extends JpaRepository<TimeSlots, Integer> {
    @Query("SELECT t FROM TimeSlots t WHERE t.pitches.id_pitches = :pitchId AND t.dayOfWeek = :dayOfWeek AND t.startTime = :startTime AND t.endTime = :endTime")
    Optional<TimeSlots> findPriceForSlot(Integer pitchId, Integer dayOfWeek, LocalTime startTime, LocalTime endTime);
}