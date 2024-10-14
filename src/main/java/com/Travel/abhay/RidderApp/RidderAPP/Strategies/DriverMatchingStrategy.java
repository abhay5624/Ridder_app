package com.Travel.abhay.RidderApp.RidderAPP.Strategies;

import com.Travel.abhay.RidderApp.RidderAPP.entities.Driver;
import com.Travel.abhay.RidderApp.RidderAPP.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findMatchingDrivers(RideRequest rideRequest);
}
