package com.qp.grocery_booking.controller;

import com.qp.grocery_booking.dto.BookingGroceryItem;
import com.qp.grocery_booking.dto.ResponseData;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class GroceryUserTnxController {

    @PostMapping("/{userId}")
    public ResponseData bookGroceries(@PathVariable Long userId, @RequestBody BookingGroceryItem bookingGroceryItem){
        return null;
    }


}
