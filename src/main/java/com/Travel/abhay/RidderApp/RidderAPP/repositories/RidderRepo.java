package com.Travel.abhay.RidderApp.RidderAPP.repositories;

import com.Travel.abhay.RidderApp.RidderAPP.entities.Ridder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RidderRepo extends JpaRepository<Ridder, Long> {
}