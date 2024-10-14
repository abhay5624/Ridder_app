package com.Travel.abhay.RidderApp.RidderAPP.services.impl;

import com.Travel.abhay.RidderApp.RidderAPP.dto.DriverDto;
import com.Travel.abhay.RidderApp.RidderAPP.dto.DriverRidedto;
import com.Travel.abhay.RidderApp.RidderAPP.dto.RidderDto;
import com.Travel.abhay.RidderApp.RidderAPP.dto.RideDto;
import com.Travel.abhay.RidderApp.RidderAPP.entities.Driver;
import com.Travel.abhay.RidderApp.RidderAPP.entities.Ride;
import com.Travel.abhay.RidderApp.RidderAPP.entities.RideRequest;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.RideRequestStatus;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.RideStatus;
import com.Travel.abhay.RidderApp.RidderAPP.exceptions.BadCredentialException;
import com.Travel.abhay.RidderApp.RidderAPP.exceptions.DriverNotAuthorizedException;
import com.Travel.abhay.RidderApp.RidderAPP.exceptions.ResourceNotFoundException;
import com.Travel.abhay.RidderApp.RidderAPP.exceptions.RunTimeConflictException;
import com.Travel.abhay.RidderApp.RidderAPP.repositories.DriverRepo;
import com.Travel.abhay.RidderApp.RidderAPP.services.DriverService;
import com.Travel.abhay.RidderApp.RidderAPP.services.PaymentService;
import com.Travel.abhay.RidderApp.RidderAPP.services.RideRequestService;
import com.Travel.abhay.RidderApp.RidderAPP.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepo driverRepo;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final PaymentService paymentService;
    @Override
    public DriverRidedto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);

        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("Ride request is not available anymore");
        }

        Driver driver = getCurrentDriver();
        if(!driver.getAvailable()){
            throw new RunTimeConflictException("Driver is not Available");
        }
        driver.setAvailable(false);
         Driver savedDriver = driverRepo.save(driver);
        return modelMapper.map(rideService.createNewRide(rideRequest,savedDriver), DriverRidedto.class);
    }

    @Override
    public RideDto cancelledRide(Long rideId) {

        Ride ride = rideService.getRideById(rideId);
        Driver driver = ride.getDriver();
        if(!driver.equals(getCurrentDriver()))
            throw new DriverNotAuthorizedException("Driver is not same");
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED))
            throw new RuntimeException("Ride can't be cancelled invalid status "+ ride.getRideStatus());
        rideService.updateRideStatus(ride,RideStatus.CANCELLED);
        updateDriverAvailability(driver.getId(),true);
        return modelMapper.map(rideService.updateRideDriver(ride,null),RideDto.class);
    }

    @Override
    public RideDto startRide(Long rideId,String otp)  {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if(!ride.getDriver().equals(driver))
            throw new DriverNotAuthorizedException("driver not matched");
        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED))
            throw new RuntimeException("Ride status is not confirmed");
        if(!ride.getOtp().equals(otp)) {
            throw new BadCredentialException("Otp does not match");
        }
        Ride savedRide = rideService.updateRideStatus(ride,RideStatus.ONGOING);



        return modelMapper.map(savedRide,RideDto.class);
    }

    @Override
    public RideDto endRide(Long rideId) {

        return null;
    }

    @Override
    public RidderDto rateRidder(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        Driver currentDriver = getCurrentDriver();
        return modelMapper.map(currentDriver,DriverDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRide(PageRequest pageRequest) {
        Driver currentDriver = getCurrentDriver();
        return rideService.getAllRideOfDriver(currentDriver,pageRequest).map(
                ride -> modelMapper.map(ride,RideDto.class)
        );
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepo.findById(2L).orElseThrow(() -> new ResourceNotFoundException("No such Driver Exists"));
    }

    @Override
    public Driver updateDriverAvailability(Long driverId, boolean available) {
        Driver driver = driverRepo.findById(driverId).orElseThrow(() -> new DriverNotAuthorizedException("Driver with id "+ driverId+ " not found"));
        driver.setAvailable(available);
        return driverRepo.save(driver);
    }
}
