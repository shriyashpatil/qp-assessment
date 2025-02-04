package com.qp.grocery_booking.repository;

import com.qp.grocery_booking.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel,Long> {
}
