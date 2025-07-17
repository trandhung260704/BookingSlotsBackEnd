package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class BookingDTO {
    private Integer idPitches;
    private Integer idUser;
    private String date;
    private String startTime;
    private String endTime;
    private String status;
}
