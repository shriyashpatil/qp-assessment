package com.qp.grocery_booking.service;

import com.qp.grocery_booking.dto.ResponseData;

public interface GroceryService {

    public ResponseData addGroceryItem(String name, Double price, Integer quantity);

    public ResponseData getGroceryItems(int pageNum,int pageSize);

    public ResponseData getGroceryItemsWithQuantity(int pageNum,int pageSize);

    public ResponseData removeGroceryItem(Long id);

    public ResponseData updateGroceryItem(Long itemId,String name, Double price, Integer quantity);
}
