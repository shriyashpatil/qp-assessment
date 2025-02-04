package com.qp.grocery_booking.util;

public class Validator {
    public static String validateGroceryItem(String name, Double price, Integer quantity) {
        if (name == null || name.trim().isEmpty()) {
            return "Item name cannot be empty";
        }
        if (price == null || price < 0) {
            return "Invalid price value";
        }
        if (quantity == null || quantity < 0) {
            return "Invalid quantity value";
        }
        return null; // No validation errors
    }
}
