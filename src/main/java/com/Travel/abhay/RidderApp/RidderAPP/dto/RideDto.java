package com.Travel.abhay.RidderApp.RidderAPP.dto;

import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.PaymentMethod;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDto {
    private Long Id;
    private PointDto pickupLocation;
    private PointDto dropOffLocation;
    private LocalDateTime createdTime;
    private RiderDto ridder;
    private DriverDto driver;
    private String otp;
    private RideStatus rideStatus;
    private PaymentMethod paymentMethod;
    private double fare;
    private  LocalDateTime startedAt;
    private  LocalDateTime EndedAt;
}
