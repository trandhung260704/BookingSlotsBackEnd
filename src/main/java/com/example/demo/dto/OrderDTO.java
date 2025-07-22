package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderDTO {
    private Integer idUser;
    private Integer idPitches;
    private String date;
    private String startTime;
    private String endTime;
    private String status;
    private BigDecimal price;
}
