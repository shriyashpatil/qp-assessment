package com.qp.grocery_booking.service;

import com.qp.grocery_booking.dto.ResponseData;

public interface GroceryService {

    public ResponseData addGroceryItem(String name, Double price, Integer quantity);

    public ResponseData getGroceryItems();

    public ResponseData removeGroceryItem(Long id);

    public void updateGroceryItem();
}
