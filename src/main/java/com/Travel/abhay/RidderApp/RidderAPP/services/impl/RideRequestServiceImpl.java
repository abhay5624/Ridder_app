package com.Travel.abhay.RidderApp.RidderAPP.services.impl;

import com.Travel.abhay.RidderApp.RidderAPP.entities.RideRequest;
import com.Travel.abhay.RidderApp.RidderAPP.exceptions.ResourceNotFoundException;
import com.Travel.abhay.RidderApp.RidderAPP.repositories.RideRequestRepo;
import com.Travel.abhay.RidderApp.RidderAPP.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepo rideRequestRepo;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepo.findById(rideRequestId).orElseThrow(() -> new ResourceNotFoundException("Ride request is not available"));
    }

    @Override
    public void update(RideRequest rideRequest) {
        RideRequest toSave =  rideRequestRepo.findById(rideRequest.getId()).orElseThrow(() -> new ResourceNotFoundException("Ride with id: "+ rideRequest.getId()+" not exist"));
        rideRequestRepo.save(rideRequest);
    }
}
