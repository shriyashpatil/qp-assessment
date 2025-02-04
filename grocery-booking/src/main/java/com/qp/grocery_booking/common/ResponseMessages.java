package com.qp.grocery_booking.common;

public enum ResponseMessages {

    // GROCERY
    GROCERY_ITEM_ADDED_SUCCESSFULLY("Grocery Item Added Successfully"),

    GROCERY_ITEM_REMOVED_SUCCESSFULLY("Grocery Item Deleted Successfully"),
    GROCERY_ITEM_FETCHED_SUCCESSFULLY("Grocery Items Fetched Successfully"),

    // USER
    USER_ADDED_SUCCESSFULLY("User Added Successfully");

    private final String value;

    ResponseMessages(String s) {
        this.value = s;
    }

    public String getValue(){
        return value;
    }

}
