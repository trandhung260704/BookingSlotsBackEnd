package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_booking")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "id_pitches", nullable = false)
    private Pitches pitches;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private String status;

    private LocalDateTime createdAt;
}
