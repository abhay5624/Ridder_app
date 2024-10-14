package com.Travel.abhay.RidderApp.RidderAPP.services;

import com.Travel.abhay.RidderApp.RidderAPP.dto.DriverDto;
import com.Travel.abhay.RidderApp.RidderAPP.dto.RidderDto;
import com.Travel.abhay.RidderApp.RidderAPP.dto.RideDto;
import com.Travel.abhay.RidderApp.RidderAPP.dto.RideRequestDto;
import com.Travel.abhay.RidderApp.RidderAPP.entities.Ridder;
import com.Travel.abhay.RidderApp.RidderAPP.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelledRide(Long rideId);

    DriverDto rateRidder(Long rideId, Integer rating);

    RidderDto getMyProfile();

    public Page<RideDto> getAllMyRide(PageRequest pageRequest);

    Ridder createNewRider(UserEntity userEntity);

    Ridder getCurrentRidder();
}
