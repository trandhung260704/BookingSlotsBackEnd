package com.example.demo.repository;

import com.example.demo.entity.BookingSlots;
import com.example.demo.entity.Pitches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingSlotsRepository extends JpaRepository<BookingSlots, Integer> {
    List<BookingSlots> findByDate(LocalDate date);
    @Query("SELECT CASE WHEN COUNT(bs) > 0 THEN TRUE ELSE FALSE END FROM BookingSlots bs " +
            "WHERE bs.pitches.id_pitches = :pitchId AND bs.date = :date " +
            "AND (bs.startTime < :endTime AND bs.endTime > :startTime)")
    boolean existsByPitchesIdAndDateAndTimeOverlap(
            @Param("pitchId") Integer pitchId,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime);
}