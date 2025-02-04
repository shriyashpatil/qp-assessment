package com.qp.grocery_booking.controller;

import com.qp.grocery_booking.dto.GroceryItem;
import com.qp.grocery_booking.dto.ResponseData;
import com.qp.grocery_booking.service.GroceryServiceImpl;
import com.qp.grocery_booking.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/groceries")
public class GroceryController {

    @Autowired
    private GroceryServiceImpl groceryService;


    @PostMapping
    public ResponseData addGroceryItem(@RequestBody GroceryItem groceryItem){
        // Validate input
        String validationError = Validator.validateGroceryItem(groceryItem.getName(),groceryItem.getPrice(),groceryItem.getQuantity());
        if (validationError != null) {
            return ResponseData.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(validationError)
                    .build();
        }
        return groceryService.addGroceryItem(groceryItem.getName(),groceryItem.getPrice(),groceryItem.getQuantity());
    }

    @GetMapping
    public ResponseData getGroceryItems(@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize){
        return groceryService.getGroceryItems(pageNum,pageSize);
    }

    @DeleteMapping("/{id}")
    public ResponseData removeGroceryItem(@PathVariable Long id){
        return groceryService.removeGroceryItem(id);
    }


    @PutMapping("/{id}")
    public ResponseData updateGroceryItem(@PathVariable Long id, @RequestBody GroceryItem groceryItem){
        // Validate input
        String validationError = Validator.validateGroceryItem(groceryItem.getName(),groceryItem.getPrice(),groceryItem.getQuantity());
        if (validationError != null) {
            return ResponseData.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(validationError)
                    .build();
        }
        return groceryService.updateGroceryItem(id,groceryItem.getName(),groceryItem.getPrice(),groceryItem.getQuantity());
    }



}
