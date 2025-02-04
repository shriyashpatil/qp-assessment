package com.qp.grocery_booking.repository;

import com.qp.grocery_booking.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel,Long> {
}
