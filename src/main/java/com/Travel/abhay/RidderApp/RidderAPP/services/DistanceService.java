package com.Travel.abhay.RidderApp.RidderAPP.services;

import org.locationtech.jts.geom.Point;

public interface DistanceService {
    double CalculateDistance(Point Source,Point Dest);
}
