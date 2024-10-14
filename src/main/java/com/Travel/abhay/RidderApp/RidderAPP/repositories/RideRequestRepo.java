package com.Travel.abhay.RidderApp.RidderAPP.repositories;

import com.Travel.abhay.RidderApp.RidderAPP.entities.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  RideRequestRepo extends JpaRepository<RideRequest,Long> {
}
