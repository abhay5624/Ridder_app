package com.Travel.abhay.RidderApp.RidderAPP.services.impl;

import com.Travel.abhay.RidderApp.RidderAPP.exceptions.OSRMException;
import com.Travel.abhay.RidderApp.RidderAPP.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceOSRMimpl implements DistanceService {

    private static final String OSRM_API = "https://router.project-osrm.org/route/v1/driving/";

    @Override
    public double CalculateDistance(Point src, Point dest) {
        try{
            String uri = src.getX()+ ","+src.getY()+";"+dest.getX()+","+dest.getY();
            OSRMResponseDto responseDto = RestClient.builder()
                    .baseUrl(OSRM_API)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .body(OSRMResponseDto.class);

            assert responseDto != null;
            return responseDto.getRoutes().getFirst().getDistance()/1000;

    }   catch(Exception ex){
            throw new OSRMException("Error getting data from OSRM" + ex.getMessage());
        }
    }
}
@Data
class OSRMResponseDto {
    private List<OSRMRoute> routes;
}
@Data
class OSRMRoute{
    private double distance;
}
