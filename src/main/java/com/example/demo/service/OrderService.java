package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PitchesRepository pitchesRepository;
    private final UsersRepository usersRepository;
    private final BookingSlotsRepository bookingSlotsRepository;

    @Transactional
    public Orders createOrder(OrderDTO request) {
        Users user = usersRepository.findById(request.getIdUser())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        Pitches pitch = pitchesRepository.findByIdForUpdate(request.getIdPitches())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sân"));

        LocalDate orderDate = LocalDate.parse(request.getDate());
        LocalTime startTime = LocalTime.parse(request.getStartTime());
        LocalTime endTime = LocalTime.parse(request.getEndTime());

        List<Orders> orderConflicts = orderRepository.findConflictingOrdersForUpdate(
                pitch.getId_pitches(), orderDate, startTime, endTime
        );

        List<BookingSlots> conflictingBookings = bookingSlotsRepository.findConflictingBookingsForUpdate(
                pitch.getId_pitches(), orderDate, startTime, endTime
        );

        if (!orderConflicts.isEmpty() || !conflictingBookings.isEmpty()) {
            return null;
        }

        String idBooking = pitch.getId_pitches() + "_" + orderDate + "_" + startTime;

        BookingSlots bookingSlot = new BookingSlots();
        bookingSlot.setId_booking(idBooking);
        bookingSlot.setId_user(user);
        bookingSlot.setId_pitches(pitch);
        bookingSlot.setDate(orderDate);
        bookingSlot.setStart_time(startTime);
        bookingSlot.setEnd_time(endTime);
        bookingSlot.setStatus("BOOKED");
        bookingSlotsRepository.save(bookingSlot);

        Orders order = new Orders();
        order.setUser(user);
        order.setPitches(pitch);
        order.setDate(orderDate);
        order.setStart_time(startTime);
        order.setEnd_time(endTime);
        order.setStatus(request.getStatus() != null ? request.getStatus() : "PENDING");
        order.setCreated_at(LocalDateTime.now());
        order.setId_booking(idBooking);
        order.setBookingSlot(bookingSlot);
        order.setPrice(request.getPrice() != null ? request.getPrice() : BigDecimal.ZERO);

        return orderRepository.save(order);
    }
}
