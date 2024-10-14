package com.Travel.abhay.RidderApp.RidderAPP.services;

import com.Travel.abhay.RidderApp.RidderAPP.dto.DriverDto;
import com.Travel.abhay.RidderApp.RidderAPP.dto.DriverRidedto;
import com.Travel.abhay.RidderApp.RidderAPP.dto.RidderDto;
import com.Travel.abhay.RidderApp.RidderAPP.dto.RideDto;
import com.Travel.abhay.RidderApp.RidderAPP.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {

    DriverRidedto acceptRide(Long rideRequestId);

    RideDto cancelledRide(Long rideId);

    RideDto  startRide(Long rideId, String otp);

    RideDto endRide(Long rideId);

    RidderDto rateRidder(Long rideId,Integer rating);

    DriverDto getMyProfile();

    Page<RideDto> getAllMyRide(PageRequest pageRequest);

    Driver getCurrentDriver();

    Driver updateDriverAvailability(Long driverId, boolean available);
}
