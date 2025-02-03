package com.qp.grocery_booking.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name="grocery_items")
public class GroceryModel extends  BaseModel{

    private String name;
    private Double price;
    private Integer quantity;

}
