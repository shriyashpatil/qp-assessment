package com.qp.grocery_booking.service;

import com.qp.grocery_booking.dto.ResponseData;

import java.util.List;

public interface GroceryUserTnxService {

    public ResponseData bookGroceries(Long userId,List<Long> ids);

}
