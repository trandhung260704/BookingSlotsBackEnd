package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class BookingRequest {
    private Integer idUser;
    private String status;
    private List<BookingSlotRequest> slots;
}
