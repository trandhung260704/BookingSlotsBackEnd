package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "timeslots")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlots {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_timeslot")
    private Integer idTimeslot;

    @ManyToOne
    @JoinColumn(name = "id_pitches")
    private Pitches pitches;

    private Integer dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    @Column(nullable = false)
    private BigDecimal price;}
