package com.qp.grocery_booking.controller;

import com.qp.grocery_booking.dto.GroceryItem;
import com.qp.grocery_booking.service.GroceryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/groceries")
public class GroceryController {

    @Autowired
    private GroceryServiceImpl groceryService;


    @PostMapping
    public void addGroceryItem(@RequestBody GroceryItem groceryItem){
        groceryService.addGroceryItem(groceryItem.getName(),groceryItem.getPrice(),groceryItem.getQuantity());
    }

    @GetMapping
    public void getGroceryItems(){
        groceryService.getGroceryItems();
    }

    @DeleteMapping("/{id}")
    public void removeGroceryItem(@PathVariable Long id){
        groceryService.removeGroceryItem();
    }



}
