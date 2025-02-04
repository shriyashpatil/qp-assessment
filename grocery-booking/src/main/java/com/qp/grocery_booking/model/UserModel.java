package com.qp.grocery_booking.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="grocery_user")
public class UserModel extends BaseModel{
    String name;
}
