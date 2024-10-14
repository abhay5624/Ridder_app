package com.Travel.abhay.RidderApp.RidderAPP.services;

 import com.Travel.abhay.RidderApp.RidderAPP.dto.RidderDto;
import com.Travel.abhay.RidderApp.RidderAPP.dto.SignupDto;
import com.Travel.abhay.RidderApp.RidderAPP.dto.UserDto;

public interface AuthService {
    String Login(String email,String password);
    UserDto Signup(SignupDto signupDto);
    RidderDto OnBoardNewDriver(Long userId);
}
