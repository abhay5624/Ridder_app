package com.Travel.abhay.RidderApp.RidderAPP.repositories;

import com.Travel.abhay.RidderApp.RidderAPP.entities.UserEntity;
import com.Travel.abhay.RidderApp.RidderAPP.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepo extends JpaRepository<Wallet,Long> {
    Wallet findByUser(UserEntity user);
}
