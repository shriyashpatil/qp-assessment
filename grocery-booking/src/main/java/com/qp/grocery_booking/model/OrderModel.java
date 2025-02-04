package com.qp.grocery_booking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name="orders")
public class OrderModel extends BaseModel{

    @OneToMany
    private List<UserGroceryTnxModel> userGroceryTnxModel;

    Double totalAmount;

    @Enumerated(EnumType.ORDINAL)
    OrderStatus orderStatus;
}
