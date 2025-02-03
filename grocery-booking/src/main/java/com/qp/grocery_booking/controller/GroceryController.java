package com.qp.grocery_booking.controller;

import com.qp.grocery_booking.dto.GroceryItem;
import com.qp.grocery_booking.dto.ResponseData;
import com.qp.grocery_booking.service.GroceryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/groceries")
public class GroceryController {

    @Autowired
    private GroceryServiceImpl groceryService;


    @PostMapping
    public ResponseData addGroceryItem(@RequestBody GroceryItem groceryItem){
        return groceryService.addGroceryItem(groceryItem.getName(),groceryItem.getPrice(),groceryItem.getQuantity());
    }

    @GetMapping
    public ResponseData getGroceryItems(){
        return groceryService.getGroceryItems();
    }

    @DeleteMapping("/{id}")
    public ResponseData removeGroceryItem(@PathVariable Long id){
        return groceryService.removeGroceryItem(id);
    }



}
