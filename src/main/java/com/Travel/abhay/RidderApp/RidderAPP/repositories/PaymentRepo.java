package com.Travel.abhay.RidderApp.RidderAPP.repositories;

import com.Travel.abhay.RidderApp.RidderAPP.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long> {
}
