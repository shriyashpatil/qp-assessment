package com.qp.grocery_booking.common;

public enum ResponseMessages {

    GROCERY_ITEM_ADDED_SUCCESSFULLY("Grocery Item Added Successfully"),

    GROCERY_ITEM_REMOVED_SUCCESSFULLY("Grocery Item Deleted Successfully"),
    GROCERY_ITEM_FETCHED_SUCCESSFULLY("Grocery Items Fetched Successfully");

    private final String value;

    ResponseMessages(String s) {
        this.value = s;
    }

    public String getValue(){
        return value;
    }

}
