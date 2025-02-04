package com.qp.grocery_booking.repository;

import com.qp.grocery_booking.model.UserGroceryTnxModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroceryTnxRepository extends JpaRepository<UserGroceryTnxModel,Long>{
}
