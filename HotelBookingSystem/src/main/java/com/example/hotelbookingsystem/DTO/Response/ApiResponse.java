package com.example.hotelbookingsystem.DTO.Response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ApiResponse<T> {
    private boolean status;
    private String message;
    private T data;
}
