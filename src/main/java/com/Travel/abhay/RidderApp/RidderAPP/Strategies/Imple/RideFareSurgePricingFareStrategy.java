package com.Travel.abhay.RidderApp.RidderAPP.Strategies.Imple;

import com.Travel.abhay.RidderApp.RidderAPP.Strategies.RideFareCalculateStategy;
import com.Travel.abhay.RidderApp.RidderAPP.entities.RideRequest;
import com.Travel.abhay.RidderApp.RidderAPP.services.DistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareSurgePricingFareStrategy implements RideFareCalculateStategy {
        private final DistanceService distanceService;
        private static final double surgeFact = 2;
        @Override
        public double calculateFare(RideRequest rideRequest ) {
            double distance = distanceService.CalculateDistance(rideRequest.getPickupLocation(),rideRequest.getDropOffLocation());
            return distance*RIDE_FARE_MULTIPLIER*surgeFact;
        }
}
