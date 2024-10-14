package com.Travel.abhay.RidderApp.RidderAPP.Strategies.Imple;

import com.Travel.abhay.RidderApp.RidderAPP.Strategies.DriverMatchingStrategy;
import com.Travel.abhay.RidderApp.RidderAPP.entities.Driver;
import com.Travel.abhay.RidderApp.RidderAPP.entities.RideRequest;
import com.Travel.abhay.RidderApp.RidderAPP.repositories.DriverRepo;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {
    private  final DriverRepo driverRepo;

    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
        Point pickUp = rideRequest.getPickupLocation();
        return driverRepo.findTenNearestDrivers(pickUp);
    }
}
