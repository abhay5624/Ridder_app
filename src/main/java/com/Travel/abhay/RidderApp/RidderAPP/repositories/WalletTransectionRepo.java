package com.Travel.abhay.RidderApp.RidderAPP.repositories;

import com.Travel.abhay.RidderApp.RidderAPP.entities.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTransectionRepo extends JpaRepository<WalletTransaction,Long> {
}
