package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "timeslots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlots {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_timeslot")
    private Integer id_timeslot;

    @ManyToOne
    @JoinColumn(name = "id_pitches")
    private Pitches id_pitches;

    @Column(name = "day_of_week")
    private Integer day_of_week;

    @Column(name = "start_time", nullable = false)
    private LocalTime start_time;

    @Column(name = "end_time", nullable = false)
    private LocalTime end_time;

    @Column(name = "price", nullable = false)
    private BigDecimal price;
}