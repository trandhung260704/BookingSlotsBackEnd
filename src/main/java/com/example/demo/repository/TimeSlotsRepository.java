package com.example.demo.repository;

import com.example.demo.entity.TimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface TimeSlotsRepository extends JpaRepository<TimeSlots, Integer> {

    @Query("SELECT t FROM TimeSlots t WHERE t.id_pitches.id_pitches = :pitchId " +
            "AND t.day_of_week = :dayOfWeek " +
            "AND t.start_time = :startTime " +
            "AND t.end_time = :endTime")
    Optional<TimeSlots> findPriceForSlot(Integer pitchId, Integer dayOfWeek, LocalTime startTime, LocalTime endTime);

}