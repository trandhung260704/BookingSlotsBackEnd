package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pitches")
@Getter
@Setter
public class Pitches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pitches;

    private String name;
    private String type;
    private String status;
}
