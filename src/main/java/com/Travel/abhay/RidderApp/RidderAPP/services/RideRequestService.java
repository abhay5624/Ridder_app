package com.Travel.abhay.RidderApp.RidderAPP.services;

import com.Travel.abhay.RidderApp.RidderAPP.entities.RideRequest;

public interface RideRequestService {
    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}
