package com.Travel.abhay.RidderApp.RidderAPP.services.impl;

import com.Travel.abhay.RidderApp.RidderAPP.dto.RideRequestDto;
import com.Travel.abhay.RidderApp.RidderAPP.entities.*;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.RideRequestStatus;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.RideStatus;
import com.Travel.abhay.RidderApp.RidderAPP.exceptions.ResourceNotFoundException;
import com.Travel.abhay.RidderApp.RidderAPP.repositories.RideRepo;
import com.Travel.abhay.RidderApp.RidderAPP.services.PaymentService;
import com.Travel.abhay.RidderApp.RidderAPP.services.RideRequestService;
import com.Travel.abhay.RidderApp.RidderAPP.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;
    private final RideRepo rideRepo;
    private final PaymentService paymentService;
    @Override
    public void matchWithDriver(RideRequestDto rideRequestDto) {

    }

    @Override
    @Transactional
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);

        Ride ride = modelMapper.map(rideRequest,Ride.class);
        ride.setDriver(driver);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setOtp(generateOtp());
        ride.setId(null);
        rideRequestService.update(rideRequest);
        paymentService.createNewPayment(ride);
        return rideRepo.save(ride);
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setStartedAt(LocalDateTime.now());
        ride.setRideStatus(rideStatus);
        return rideRepo.save(ride);
    }

    @Override
    public Page<Ride> getAllRideOfRidder(Ridder rider, PageRequest pageRequest) {
        return rideRepo.findByRidder(rider,pageRequest);
    }

    @Override
    public Page<Ride> getAllRideOfDriver(Driver driver, PageRequest pageRequest) {
        return rideRepo.findByDriver(driver,pageRequest);
    }

    @Override
    public Ride getRideById(Long rideId) {
        return rideRepo.findById(rideId).orElseThrow(() -> new ResourceNotFoundException("Ride with id "+ rideId+ " is not present"));
    }

    @Override
    public Ride updateRideDriver(Ride ride, Driver driver) {
        ride.setDriver(driver);
        return rideRepo.save(ride);
    }


    private String generateOtp(){
        Random random = new Random();
        int otpInt =  random.nextInt(10000);
        return String.format("%04d", otpInt);

    }
}
