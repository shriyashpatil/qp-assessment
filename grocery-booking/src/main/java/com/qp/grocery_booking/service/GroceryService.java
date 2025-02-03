package com.qp.grocery_booking.service;

public interface GroceryService {

    public void addGroceryItem(String name,Double price,Integer quantity);

    public void getGroceryItems();

    public void removeGroceryItem();

    public void updateGroceryItem();
}
