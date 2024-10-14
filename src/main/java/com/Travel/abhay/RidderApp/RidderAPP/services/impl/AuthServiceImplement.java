package com.Travel.abhay.RidderApp.RidderAPP.services.impl;

import com.Travel.abhay.RidderApp.RidderAPP.dto.RidderDto;
import com.Travel.abhay.RidderApp.RidderAPP.dto.SignupDto;
import com.Travel.abhay.RidderApp.RidderAPP.dto.UserDto;
import com.Travel.abhay.RidderApp.RidderAPP.entities.UserEntity;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.Roles;
import com.Travel.abhay.RidderApp.RidderAPP.exceptions.UserSignupException;
import com.Travel.abhay.RidderApp.RidderAPP.repositories.UserRepo;
import com.Travel.abhay.RidderApp.RidderAPP.services.AuthService;
import com.Travel.abhay.RidderApp.RidderAPP.services.RiderService;
import com.Travel.abhay.RidderApp.RidderAPP.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepo userRepo;
    private final RiderService riderService;
    private final WalletService walletService;
    @Override
    public String Login(String email, String password) {
        return "";
    }

    @Override
    @Transactional
    public UserDto Signup(SignupDto signupDto) {
        UserEntity user = userRepo.findByEmail(signupDto.getEmail()).orElse(null);
        if(user != null){
            throw new UserSignupException("User already exist");
        }
        user = modelMapper.map(signupDto, UserEntity.class);
        user.setRoles(Set.of(Roles.RIDER));
        UserEntity saverdUser  = userRepo.save(user);
        riderService.createNewRider(saverdUser);
        walletService.createNewWallet(saverdUser);

        return modelMapper.map(saverdUser,UserDto.class);
    }

    @Override
    public RidderDto OnBoardNewDriver(Long userId) {
        return null;
    }
}
