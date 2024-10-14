package com.Travel.abhay.RidderApp.RidderAPP.controllers;


import com.Travel.abhay.RidderApp.RidderAPP.dto.SignupDto;
import com.Travel.abhay.RidderApp.RidderAPP.dto.UserDto;
import com.Travel.abhay.RidderApp.RidderAPP.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    UserDto signup(@RequestBody SignupDto signupDto){
        return authService.Signup(signupDto);
    }
}
