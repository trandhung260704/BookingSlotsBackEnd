package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "bookingslots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingSlots {

    @Id
    @Column(name = "id_booking", length = 100)
    private String id_booking; // PK dạng: idpitches_date_starttime

    @ManyToOne
    @JoinColumn(name = "id_pitches")
    private Pitches id_pitches;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime start_time;

    @Column(name = "end_time", nullable = false)
    private LocalTime end_time;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users id_user;

    @Column(name = "status", length = 20)
    private String status;
}