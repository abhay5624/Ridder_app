package com.Travel.abhay.RidderApp.RidderAPP.Strategies;

import com.Travel.abhay.RidderApp.RidderAPP.entities.RideRequest;

public interface RideFareCalculateStategy {
    double RIDE_FARE_MULTIPLIER = 10;
    double calculateFare(RideRequest rideRequest);
}
