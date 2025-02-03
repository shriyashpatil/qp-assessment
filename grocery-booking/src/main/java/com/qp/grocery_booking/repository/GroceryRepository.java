package com.qp.grocery_booking.repository;

import com.qp.grocery_booking.model.GroceryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryRepository extends JpaRepository<GroceryModel,Long> {
}
