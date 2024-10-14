package com.Travel.abhay.RidderApp.RidderAPP.Strategies.Imple;

import com.Travel.abhay.RidderApp.RidderAPP.Strategies.DriverMatchingStrategy;
import com.Travel.abhay.RidderApp.RidderAPP.entities.Driver;
import com.Travel.abhay.RidderApp.RidderAPP.entities.RideRequest;
import com.Travel.abhay.RidderApp.RidderAPP.repositories.DriverRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional()
public class DriverMatchingHigestRatedDriverStategy implements DriverMatchingStrategy {

    private final DriverRepo driverRepo;

    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
         return driverRepo.findTenNearbyTopRatedDrivers(rideRequest.getPickupLocation());
    }
}

