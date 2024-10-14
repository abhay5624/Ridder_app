package com.Travel.abhay.RidderApp.RidderAPP.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {
    private Long Id;
    private UserDto userDto;
    private Double Rating;
    private Boolean available;
    private String vehicleId;
}
