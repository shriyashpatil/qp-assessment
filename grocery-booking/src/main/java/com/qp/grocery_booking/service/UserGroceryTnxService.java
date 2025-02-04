package com.qp.grocery_booking.service;

import com.qp.grocery_booking.dto.ResponseData;

import java.util.List;

public interface UserGroceryTnxService {

    public ResponseData bookGroceries(Long userId,List<Long> ids);

    public ResponseData getGroceries();

}
