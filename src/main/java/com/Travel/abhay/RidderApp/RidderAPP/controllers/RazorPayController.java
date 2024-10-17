package com.Travel.abhay.RidderApp.RidderAPP.controllers;

import com.Travel.abhay.RidderApp.RidderAPP.advices.ApiResponse;
import com.Travel.abhay.RidderApp.RidderAPP.services.RazorPayService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/transferAmount")
@RequiredArgsConstructor
public class RazorPayController {

    private final RazorPayService razorPayService;


    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/userId/{userId}")
    public ResponseEntity<ApiResponse<?>> TransferToWallet(@RequestBody String requestBody) throws RazorpayException{
        JSONObject jsonObject = new JSONObject(requestBody);
        String amountString = jsonObject.getString("amount");
        int amountInt = Integer.parseInt(amountString);
        return ResponseEntity.ok(new ApiResponse<>(razorPayService.transferMoney(amountInt)));
    }
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/verify/{userId}")
    public ResponseEntity<ApiResponse<?>> verifyPayment(@RequestBody Map<String,String> data,@PathVariable Long userId) throws BadRequestException, RazorpayException {
        return ResponseEntity.ok(new ApiResponse<>(razorPayService.verifyPayment(data,userId)));
    }
}
