package com.qp.grocery_booking.service;

import com.qp.grocery_booking.dto.ResponseData;

public interface UserService {

    public ResponseData addUser(String name);

    public ResponseData removeUser(Long id);

    public ResponseData updateUser(Long id,String name);

}
