package com.qp.grocery_booking.controller;

import com.qp.grocery_booking.dto.ResponseData;
import com.qp.grocery_booking.dto.User;
import com.qp.grocery_booking.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping
    public ResponseData addUser(@RequestBody User user){
        return userService.addUser(user.getName());
    }

}
