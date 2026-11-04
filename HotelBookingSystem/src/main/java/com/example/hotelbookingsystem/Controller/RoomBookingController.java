package com.example.hotelbookingsystem.Controller;

import com.example.hotelbookingsystem.DTO.Request.RoomBookingRequest;
import com.example.hotelbookingsystem.DTO.Response.ApiResponse;
import com.example.hotelbookingsystem.DTO.Response.RoomBookingResponse;
import com.example.hotelbookingsystem.Model.RoomBooking;
import com.example.hotelbookingsystem.Service.RoomBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomBookingController {
    private final RoomBookingService roomBookingService;

    //khach hang dat phong
    @PostMapping("/{roomId}/bookings")
    public ResponseEntity<ApiResponse<RoomBookingResponse>> addRoomBooking(@PathVariable Long roomId, @RequestBody RoomBookingRequest req) {
        RoomBookingResponse resp = roomBookingService.bookroom(roomId, req);

        ApiResponse<RoomBookingResponse> response = ApiResponse.<RoomBookingResponse>builder()
                .status(true)
                .message("201 CREATED")
                .data(resp).build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
