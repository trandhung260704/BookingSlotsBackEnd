package com.example.demo.repository;

import com.example.demo.entity.Orders;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

    @Query("SELECT o FROM Orders o WHERE o.pitches.id_pitches = :pitchId " +
            "AND o.date = :date " +
            "AND (o.start_time < :endTime AND o.end_time > :startTime)")
    List<Orders> findConflictingOrders(
            @Param("pitchId") Integer pitchId,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT o FROM Orders o WHERE o.pitches.id_pitches = :pitchId " +
            "AND o.date = :date " +
            "AND (o.start_time < :endTime AND o.end_time > :startTime)")
    List<Orders> findConflictingOrdersForUpdate(
            @Param("pitchId") Integer pitchId,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime);

    @Query("SELECT o FROM Orders o WHERE o.pitches.id_pitches = :pitchId AND o.date = :date " +
            "AND ((o.start_time < :endTime AND o.end_time > :startTime))")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Orders> findConflictingOrdersForUpdate(@Param("pitchId") String pitchId,
                                                @Param("date") LocalDate date,
                                                @Param("startTime") LocalTime startTime,
                                                @Param("endTime") LocalTime endTime);

}