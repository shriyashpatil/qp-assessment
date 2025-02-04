package com.qp.grocery_booking.controller;

import com.qp.grocery_booking.dto.BookingGroceryItem;
import com.qp.grocery_booking.dto.ResponseData;
import com.qp.grocery_booking.service.UserGroceryTnxServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class GroceryUserTnxController {

    @Autowired
    UserGroceryTnxServiceImpl userGroceryTnxService;

    @PostMapping("/book/{userId}")
    public ResponseData bookGroceries(@PathVariable Long userId, @RequestBody BookingGroceryItem bookingGroceryItem){
        return userGroceryTnxService.bookGroceries(userId,bookingGroceryItem.getIds());
    }

    @GetMapping("/get-groceries")
    public ResponseData getGroceries(){
        return userGroceryTnxService.getGroceries();
    }
}
