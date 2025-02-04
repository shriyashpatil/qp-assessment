package com.qp.grocery_booking.repository;

import com.qp.grocery_booking.model.GroceryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroceryRepository extends JpaRepository<GroceryModel,Long> {

    @Query(value = "select * from grocery_items where id in(?1) and quantity>0",nativeQuery = true)
    Optional<List<GroceryModel>> getGroceriesById(List<Long> id);

    @Query(value = "select * from grocery_items where quantity>0",nativeQuery = true)
    Optional<List<GroceryModel>> getGroceryWithQuantity();

}
