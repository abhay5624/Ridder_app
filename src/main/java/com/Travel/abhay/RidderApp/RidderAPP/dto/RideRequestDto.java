package com.Travel.abhay.RidderApp.RidderAPP.dto;

import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.PaymentMethod;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {

    private long Id;

     private PointDto pickupLocation;

     private PointDto dropOffLocation;

     private LocalDateTime RequestedTime;

     private RidderDto ridder;

     private RideRequestStatus rideRequestStatus;

     private PaymentMethod paymentMethod;

     private double fare;


}
