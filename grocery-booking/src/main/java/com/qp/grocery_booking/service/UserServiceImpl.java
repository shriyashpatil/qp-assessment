package com.qp.grocery_booking.service;

import com.qp.grocery_booking.common.ResponseMessages;
import com.qp.grocery_booking.dto.GroceryItemResponse;
import com.qp.grocery_booking.dto.ResponseData;
import com.qp.grocery_booking.model.UserModel;
import com.qp.grocery_booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseData addUser(String name) {

        UserModel userModel = new UserModel();
        userModel.setName(name);

        UserModel result = userRepository.save(userModel);

        return ResponseData.builder()
                .status(HttpStatus.OK)
                .data(GroceryItemResponse.builder().id(result.getId()).build())
                .message(ResponseMessages.USER_ADDED_SUCCESSFULLY.getValue())
                .build();
    }

    @Override
    public ResponseData removeUser(Long id) {
        return null;
    }

    @Override
    public ResponseData updateUser(Long id, String name) {
        return null;
    }
}
