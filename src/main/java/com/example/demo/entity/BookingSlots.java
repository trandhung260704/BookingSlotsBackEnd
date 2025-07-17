package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "bookingslots")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingSlots {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_slot")
    private Integer idSlot;

    @ManyToOne
    @JoinColumn(name = "id_pitches")
    private Pitches pitches;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "id_booking")
    private Booking booking;

    private String status;
}
