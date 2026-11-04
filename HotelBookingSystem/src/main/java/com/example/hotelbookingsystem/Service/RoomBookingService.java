package com.example.hotelbookingsystem.Service;

import com.example.hotelbookingsystem.CustomException.BadRequestException;
import com.example.hotelbookingsystem.CustomException.ResourceNotFoundException;
import com.example.hotelbookingsystem.DTO.Request.RoomBookingRequest;
import com.example.hotelbookingsystem.DTO.Response.RoomBookingResponse;
import com.example.hotelbookingsystem.Enum.BookingStatus;
import com.example.hotelbookingsystem.Enum.RoomStatus;
import com.example.hotelbookingsystem.Model.Customer;
import com.example.hotelbookingsystem.Model.Room;
import com.example.hotelbookingsystem.Model.RoomBooking;
import com.example.hotelbookingsystem.Repository.CustomerRepository;
import com.example.hotelbookingsystem.Repository.RoomBookingRepository;
import com.example.hotelbookingsystem.Repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomBookingService {
    private final RoomBookingRepository roomBookingRepository;
    private final RoomRepository roomRepository;
    private final CustomerRepository customerRepository;

    //Dat phong
    @Transactional
    public RoomBookingResponse bookroom(Long roomId, RoomBookingRequest req) {
        //Kiem tra customer co ton tai
        Customer customer = customerRepository.findById(req.getCustomerId())
                .orElseThrow(() ->  new  ResourceNotFoundException("Không tìm thấy khách hàng trong danh sách!"));

        //Kiem tra co ton tai phong khong
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tồn tại phòng!"));

        //Kiem tra trang thai phong da muon chua
        if(room.getRoomStatus() != RoomStatus.AVAILABLE) {
            throw new BadRequestException("Phòng đã được đặt!");
        }

        //Kiem tra khach hang da dat phong do hay chua
        boolean check = roomBookingRepository.existsByRoomIdAndCustomerId(roomId,req.getCustomerId(), BookingStatus.BOOKED);
        if(check){
            throw new BadRequestException("Khách đã đặt phòng này rồi!");
        }

        //Tao RoomBooking moi
        RoomBooking roomBooking = RoomBooking.builder()
                .room(room)
                .customer(customer)
                .status(BookingStatus.BOOKED)
                .build();

        //Luu vao csdl
        RoomBooking savedRoomBooking = roomBookingRepository.save(roomBooking);

        //Tra du lieu response
        return RoomBookingResponse.builder()
                .bookingId(savedRoomBooking.getId())
                .customerName(customer.getFullname())
                .roomNumber(room.getRoomNumber())
                .status(savedRoomBooking.getStatus())
                .bookingDate(savedRoomBooking.getRoomDate())
                .build();
    }
}
