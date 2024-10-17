package com.Travel.abhay.RidderApp.RidderAPP.services;

import com.Travel.abhay.RidderApp.RidderAPP.entities.UserEntity;

public interface UserService {
    UserEntity findUserById(Long userId);
}
