package com.Travel.abhay.RidderApp.RidderAPP.services;

import com.Travel.abhay.RidderApp.RidderAPP.dto.*;
import com.Travel.abhay.RidderApp.RidderAPP.entities.Rider;
import com.Travel.abhay.RidderApp.RidderAPP.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelledRide(Long rideId);

    DriverDto rateDriver(RatingDto ratingDto);

    RiderDto getMyProfile();

    public Page<RideDto> getAllMyRide(PageRequest pageRequest);

    Rider createNewRider(UserEntity userEntity);

    Rider getCurrentRidder();
}
