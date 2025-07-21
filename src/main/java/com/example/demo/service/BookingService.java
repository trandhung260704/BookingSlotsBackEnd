package com.example.demo.service;

import com.example.demo.dto.BookingDTO;
import com.example.demo.entity.Booking;
import com.example.demo.entity.Pitches;
import com.example.demo.entity.Users;
import com.example.demo.entity.BookingSlots;
import com.example.demo.repository.BookingSlotsRepository;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.PitchesRepository;
import com.example.demo.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PitchesRepository pitchesRepository;
    private final UsersRepository usersRepository;
    private final BookingSlotsRepository bookingSlotsRepository;

    @Transactional
    public Booking createBooking(BookingDTO request) {
        Users user = usersRepository.findById(request.getIdUser())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        LocalDate bookingDate = LocalDate.parse(request.getDate());
        LocalTime start = LocalTime.parse(request.getStartTime());
        LocalTime end = LocalTime.parse(request.getEndTime());

        Pitches pitch = pitchesRepository.findById(request.getIdPitches())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sân"));

        List<Booking> conflicts = bookingRepository.findConflictingBookingsForUpdate(
                request.getIdPitches(), bookingDate, start, end
        );

        if (!conflicts.isEmpty()) {
            throw new RuntimeException("Khung giờ đã được đặt: " + request.getStartTime() + " - " + request.getEndTime());
        }

        Booking booking = new Booking();
        booking.setPitches(pitch);
        booking.setUser(user);
        booking.setDate(bookingDate);
        booking.setStartTime(start);
        booking.setEndTime(end);
        booking.setStatus(request.getStatus());
        booking.setCreatedAt(LocalDateTime.now());

        bookingRepository.save(booking);

        BookingSlots bookingSlot = new BookingSlots();
        bookingSlot.setBooking(booking);
        bookingSlot.setUser(user);
        bookingSlot.setPitches(pitch);
        bookingSlot.setDate(bookingDate);
        bookingSlot.setStartTime(start);
        bookingSlot.setEndTime(end);
        bookingSlot.setStatus("BOOKED");

        bookingSlotsRepository.save(bookingSlot);

        return booking;
    }
}
