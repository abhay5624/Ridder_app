package com.Travel.abhay.RidderApp.RidderAPP.services;

import com.Travel.abhay.RidderApp.RidderAPP.dto.RideRequestDto;
import com.Travel.abhay.RidderApp.RidderAPP.entities.Driver;
import com.Travel.abhay.RidderApp.RidderAPP.entities.Ridder;
import com.Travel.abhay.RidderApp.RidderAPP.entities.Ride;
import com.Travel.abhay.RidderApp.RidderAPP.entities.RideRequest;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {
    void matchWithDriver(RideRequestDto rideRequestDto);
    Ride createNewRide(RideRequest rideRequest, Driver driver);
    Ride updateRideStatus(Ride ride, RideStatus rideStatus);
    Page<Ride> getAllRideOfRidder(Ridder rider, PageRequest pageRequest);
    Page<Ride> getAllRideOfDriver(Driver driver, PageRequest pageRequest);
    Ride getRideById(Long rideId);
    Ride updateRideDriver(Ride ride, Driver driver);
}
