package com.qp.grocery_booking.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class GroceryItem {
    private String name;
    private  Double price;
    private Integer quantity;
}
