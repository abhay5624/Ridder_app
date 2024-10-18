 package com.Travel.abhay.RidderApp.RidderAPP.services.impl;

 import com.Travel.abhay.RidderApp.RidderAPP.Strategies.RideStrategyManager;
 import com.Travel.abhay.RidderApp.RidderAPP.dto.*;
 import com.Travel.abhay.RidderApp.RidderAPP.entities.*;
 import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.RideRequestStatus;
 import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.RideStatus;
 import com.Travel.abhay.RidderApp.RidderAPP.exceptions.ResourceNotFoundException;
 import com.Travel.abhay.RidderApp.RidderAPP.exceptions.RiderNotAuthorizedException;
 import com.Travel.abhay.RidderApp.RidderAPP.repositories.RidderRepo;
 import com.Travel.abhay.RidderApp.RidderAPP.repositories.RideRequestRepo;
 import com.Travel.abhay.RidderApp.RidderAPP.services.DriverService;
 import com.Travel.abhay.RidderApp.RidderAPP.services.RideService;
 import com.Travel.abhay.RidderApp.RidderAPP.services.RiderService;
 import lombok.RequiredArgsConstructor;
 import lombok.extern.slf4j.Slf4j;
 import org.modelmapper.ModelMapper;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;

 import java.util.List;

 @Slf4j
 @Service
 @RequiredArgsConstructor
public class RidderServiceImpl implements RiderService{

     private final ModelMapper modelMapper;
     private final RideRequestRepo rideRequestRepo;
     private  final RidderRepo ridderRepo;
     private final RideStrategyManager strategyManager;
     private final RideService rideService;
     private final DriverService driverService;



     @Override
     @Transactional
     public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
         Rider rider = getCurrentRidder();
         RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
         rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
         rideRequest.setRider(rider);
         Double fare = strategyManager.rideFareCalculateStategy().calculateFare(rideRequest);
         rideRequest.setFare(fare);
         List<Driver> drivers = strategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDrivers(rideRequest);
         RideRequest savedRideRequest = rideRequestRepo.save(rideRequest);

//        TODO : Send notification to all the drivers about this ride request

         return modelMapper.map(savedRideRequest, RideRequestDto.class);
     }

    @Override
    public RideDto cancelledRide(Long rideId) {
         Rider rider = getCurrentRidder();
         Ride ride = rideService.getRideById(rideId);
         if(!rider.equals(ride.getRider()))
            throw new RiderNotAuthorizedException("Rider does not own Ride");
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED))
            throw new RuntimeException("Ride can't be cancelled invalid status "+ ride.getRideStatus());

        rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        Driver driver = ride.getDriver();
        driverService.updateDriverAvailability(driver.getId(),true);
        return modelMapper.map(rideService.updateRideDriver(ride,null),RideDto.class);
    }

    @Override
    public DriverDto rateDriver(RatingDto ratingDto) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        Rider currentRider = getCurrentRidder();
        return modelMapper.map(currentRider, RiderDto.class);

    }

    @Override
    public Page<RideDto> getAllMyRide(PageRequest pageRequest) {
        Rider currentRider = getCurrentRidder();
        return rideService.getAllRideOfRidder(currentRider,pageRequest).map(
                ride -> modelMapper.map(ride,RideDto.class)
        );
     }

     @Override
     public Rider createNewRider(UserEntity userEntity) {
         Rider rider = Rider
                 .builder()
                 .user(userEntity)
                 .rating(0.0)
                 .build();
         return ridderRepo.save(rider);

     }

     @Override
     public Rider getCurrentRidder() {
         return ridderRepo.findById(1L).orElseThrow(() -> new ResourceNotFoundException("Rider with id 1 not exist"));
     }
 }
