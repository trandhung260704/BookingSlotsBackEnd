package com.example.demo.repository;

import com.example.demo.entity.BookingSlots;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingSlotsRepository extends JpaRepository<BookingSlots, String> {

    List<BookingSlots> findByDate(LocalDate date);

    @Query("SELECT CASE WHEN COUNT(bs) > 0 THEN TRUE ELSE FALSE END FROM BookingSlots bs " +
            "WHERE bs.id_pitches.id_pitches = :pitchId AND bs.date = :date " +
            "AND (bs.start_time < :endTime AND bs.end_time > :startTime)")
    boolean existsById_pitchesAndDateAndTimeOverlap(
            @Param("pitchId") Integer pitchId,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b FROM BookingSlots b WHERE b.id_pitches.id_pitches = :pitchId " +
            "AND b.date = :date " +
            "AND ((b.start_time < :endTime AND b.end_time > :startTime))")
    List<BookingSlots> findConflictingBookingsForUpdate(@Param("pitchId") Integer pitchId,
                                                        @Param("date") LocalDate date,
                                                        @Param("startTime") LocalTime startTime,
                                                        @Param("endTime") LocalTime endTime);
}