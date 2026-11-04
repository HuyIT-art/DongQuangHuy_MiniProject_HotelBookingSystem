package com.example.hotelbookingsystem.Repository;


import com.example.hotelbookingsystem.Enum.BookingStatus;
import com.example.hotelbookingsystem.Model.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking,Long> {
    boolean existsByRoomIdAndCustomerId(Long roomId, Long customerId, BookingStatus status);
}
