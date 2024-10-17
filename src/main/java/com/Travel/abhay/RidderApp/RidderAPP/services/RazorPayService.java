package com.Travel.abhay.RidderApp.RidderAPP.services;

import com.razorpay.RazorpayException;
import org.apache.coyote.BadRequestException;

import java.util.Map;

public interface RazorPayService {
    String transferMoney(int amount) throws RazorpayException;
    String verifyPayment(Map<String, String> data,Long userId) throws RazorpayException, BadRequestException;
}