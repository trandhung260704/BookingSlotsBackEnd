package com.example.demo.repository;

import com.example.demo.entity.BookingSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingSlotsRepository extends JpaRepository<BookingSlots, String> {

    List<BookingSlots> findByDate(LocalDate date);

    @Query(nativeQuery = true, value = "SELECT pg_advisory_xact_lock(hashtext(?1))")
    void transactionLockByIdBooking(String id_booking);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN TRUE ELSE FALSE END FROM BookingSlots b WHERE b.id_booking = :id")
    boolean existsById_booking(@Param("id") String idbooking);
}