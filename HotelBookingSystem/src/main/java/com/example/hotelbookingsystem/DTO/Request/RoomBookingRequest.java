package com.example.hotelbookingsystem.DTO.Request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RoomBookingRequest {
    private Long customerId;
}
