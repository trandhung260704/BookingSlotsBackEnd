package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class BookingSlotRequest {
    private Integer idPitches;
    private String date;
    private String startTime;
    private String endTime;
}
