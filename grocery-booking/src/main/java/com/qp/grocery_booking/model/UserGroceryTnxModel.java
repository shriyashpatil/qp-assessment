package com.qp.grocery_booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="user_grocery_tnx")
public class UserGroceryTnxModel extends BaseModel{

    @ManyToOne
    private UserModel user;

    @ManyToOne
    private GroceryModel grocery;

    private int amount;


}
