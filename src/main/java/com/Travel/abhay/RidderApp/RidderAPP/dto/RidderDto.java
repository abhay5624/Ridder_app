package com.Travel.abhay.RidderApp.RidderAPP.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RidderDto {
    private UserDto userDto;
    private Double Rating;
}