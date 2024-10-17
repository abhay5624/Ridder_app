package com.Travel.abhay.RidderApp.RidderAPP.services.impl;

import com.Travel.abhay.RidderApp.RidderAPP.entities.UserEntity;
import com.Travel.abhay.RidderApp.RidderAPP.exceptions.BadCredentialException;
import com.Travel.abhay.RidderApp.RidderAPP.repositories.UserRepo;
import com.Travel.abhay.RidderApp.RidderAPP.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    @Override
    public UserEntity findUserById(Long userId) {
        UserEntity user= userRepo.findById(userId).orElseThrow(() -> new BadCredentialException("No user with ID "+ userId+ " found"));
        return user;
    }
}
