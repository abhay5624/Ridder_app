package com.Travel.abhay.RidderApp.RidderAPP.repositories;

import com.Travel.abhay.RidderApp.RidderAPP.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);
}
