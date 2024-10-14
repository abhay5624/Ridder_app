package com.Travel.abhay.RidderApp.RidderAPP.Strategies.Imple;

import com.Travel.abhay.RidderApp.RidderAPP.Strategies.RideFareCalculateStategy;
import com.Travel.abhay.RidderApp.RidderAPP.entities.RideRequest;
import com.Travel.abhay.RidderApp.RidderAPP.services.DistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculateStategy {
    private final DistanceService distanceService;
    @Override
    public double calculateFare(RideRequest rideRequest ) {
        Double distance = distanceService.CalculateDistance(rideRequest.getPickupLocation(),rideRequest.getDropOffLocation());

        return distance*RIDE_FARE_MULTIPLIER;
    }
}
