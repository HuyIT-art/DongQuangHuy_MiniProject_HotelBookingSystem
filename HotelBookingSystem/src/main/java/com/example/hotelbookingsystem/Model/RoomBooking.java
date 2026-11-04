package com.example.hotelbookingsystem.Model;

import com.example.hotelbookingsystem.Enum.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "roombooking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RoomBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private LocalDateTime roomDate;
    @PrePersist
    public void prePersist() {
        this.roomDate = LocalDateTime.now();
    }
}
