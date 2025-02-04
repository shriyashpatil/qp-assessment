package com.qp.grocery_booking.service;

import com.qp.grocery_booking.common.ResponseMessages;
import com.qp.grocery_booking.dto.GroceryItemResponse;
import com.qp.grocery_booking.dto.ResponseData;
import com.qp.grocery_booking.exception.GroceryOutOfStockException;
import com.qp.grocery_booking.exception.NoGroceryItemAvailable;
import com.qp.grocery_booking.model.GroceryModel;
import com.qp.grocery_booking.repository.GroceryRepository;
import com.qp.grocery_booking.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public ResponseData getGroceryItems(int pageNum,int pageSize) {
        List<GroceryModel>  groceryItems =   groceryRepository.getGroceries(pageSize,pageNum*pageSize);
        return ResponseData.builder()
                .status(HttpStatus.OK)
                .data(groceryItems)
                .message(ResponseMessages.GROCERY_ITEM_FETCHED_SUCCESSFULLY.getValue())
                .build();

    }

    @Override
    public ResponseData getGroceryItemsWithQuantity(int pageNum, int pageSize) {
        try {
            System.out.println(pageNum+""+pageSize);
            Optional<List<GroceryModel>> groceryItemsOptional = Optional.of(groceryRepository.getGroceryWithQuantity(pageSize,pageNum*pageSize));

            if (groceryItemsOptional.isEmpty() || groceryItemsOptional.get().isEmpty()) {
                throw new GroceryOutOfStockException("No grocery items available in stock.");
            }

            return ResponseData.builder()
                    .status(HttpStatus.OK)
                    .data(groceryItemsOptional.get())
                    .message(ResponseMessages.GROCERY_ITEM_FETCHED_SUCCESSFULLY.getValue())
                    .build();

        } catch (GroceryOutOfStockException e) {
            return ResponseData.builder()
                    .status(HttpStatus.OK)
                    .data(Collections.EMPTY_LIST)
                    .message(e.getMessage())
                    .build();
        }
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
    public ResponseData updateGroceryItem(Long itemId, String name, Double price, Integer quantity) {
        try {
            // Fetch grocery item
            GroceryModel grocery = groceryRepository.findById(itemId)
                    .orElseThrow(() -> new NoGroceryItemAvailable("Grocery item not found with ID: " + itemId));


            // Update fields
            grocery.setName(name);
            grocery.setPrice(price);
            grocery.setQuantity(quantity);
            GroceryModel updatedGrocery = groceryRepository.save(grocery);

            return ResponseData.builder()
                    .status(HttpStatus.OK)
                    .data(GroceryItemResponse.builder().id(updatedGrocery.getId()).build())
                    .message(ResponseMessages.GROCERY_ITEM_UPDATED_SUCCESSFULLY.getValue())
                    .build();

        } catch (NoGroceryItemAvailable e) {
            return ResponseData.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }

}
