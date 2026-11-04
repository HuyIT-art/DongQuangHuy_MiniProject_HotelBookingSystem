package com.example.hotelbookingsystem.DTO.Response;

import com.example.hotelbookingsystem.Enum.BookingStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RoomBookingResponse {
    private Long bookingId;
    private String roomNumber;
    private String customerName;
    private LocalDateTime bookingDate;
    private BookingStatus status;
}
