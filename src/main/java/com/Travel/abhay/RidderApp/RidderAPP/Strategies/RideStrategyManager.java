package com.Travel.abhay.RidderApp.RidderAPP.Strategies;


import com.Travel.abhay.RidderApp.RidderAPP.Strategies.Imple.DriverMatchingHigestRatedDriverStategy;
import com.Travel.abhay.RidderApp.RidderAPP.Strategies.Imple.DriverMatchingNearestDriverStrategy;
import com.Travel.abhay.RidderApp.RidderAPP.Strategies.Imple.RideFareDefaultFareCalculationStrategy;
import com.Travel.abhay.RidderApp.RidderAPP.Strategies.Imple.RideFareSurgePricingFareStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;


@Component
@RequiredArgsConstructor
public class RideStrategyManager {
    private final DriverMatchingHigestRatedDriverStategy driverMatchingHigestRatedDriverStategy;
    private final DriverMatchingNearestDriverStrategy driverMatchingNearestDriverStrategy;
    private final RideFareDefaultFareCalculationStrategy rideFareDefaultFareCalculationStrategy;
    private final RideFareSurgePricingFareStrategy rideFareSurgePricingFareStrategy;
    public DriverMatchingStrategy driverMatchingStrategy(double riderRating){
        if(riderRating >= 4.8){
            return driverMatchingHigestRatedDriverStategy;
        }
        else
            return driverMatchingNearestDriverStrategy;
    }

    public RideFareCalculateStategy rideFareCalculateStategy(){
        LocalTime surgesStartTime = LocalTime.of(18,0);
        LocalTime surgesEndTime = LocalTime.of(21,0);
        LocalTime currentTime = LocalTime.now();
        boolean isSurgeTime= currentTime.isAfter(surgesStartTime) && currentTime.isBefore(surgesEndTime);
        if(isSurgeTime) return rideFareSurgePricingFareStrategy;
        else return rideFareDefaultFareCalculationStrategy;
    }
}
