package com.qp.grocery_booking.service;

import com.qp.grocery_booking.common.ResponseMessages;
import com.qp.grocery_booking.dto.GroceryItemResponse;
import com.qp.grocery_booking.dto.ResponseData;
import com.qp.grocery_booking.model.GroceryModel;
import com.qp.grocery_booking.repository.GroceryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryServiceImpl implements GroceryService {

    @Autowired
    GroceryRepository groceryRepository;

    @Override
    public ResponseData addGroceryItem(String name, Double price, Integer quantity) {

        GroceryModel groceryModel = new GroceryModel();
        groceryModel.setName(name);
        groceryModel.setPrice(price);
        groceryModel.setQuantity(quantity);
        GroceryModel result = groceryRepository.save(groceryModel);

        return ResponseData.builder()
                .status(HttpStatus.OK)
                .data(GroceryItemResponse.builder().id(result.getId()).build())
                .message(ResponseMessages.GROCERY_ITEM_ADDED_SUCCESSFULLY.getValue())
                .build();

    }

    @Override
    public ResponseData getGroceryItems() {
        List<GroceryModel>  groceryItems =   groceryRepository.findAll();
        return ResponseData.builder()
                .status(HttpStatus.OK)
                .data(groceryItems)
                .message(ResponseMessages.GROCERY_ITEM_FETCHED_SUCCESSFULLY.getValue())
                .build();

    }

    @Override
    public ResponseData removeGroceryItem(Long id) {
        groceryRepository.deleteById(id);
        return ResponseData.builder()
                .status(HttpStatus.OK)
                .data(GroceryItemResponse.builder().id(id).build())
                .message(ResponseMessages.GROCERY_ITEM_REMOVED_SUCCESSFULLY.getValue())
                .build();
    }

    @Override
    public void updateGroceryItem() {

    }
}
