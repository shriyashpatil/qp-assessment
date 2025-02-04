package com.qp.grocery_booking.service;

import com.qp.grocery_booking.common.ResponseMessages;
import com.qp.grocery_booking.dto.OrderResponse;
import com.qp.grocery_booking.dto.ResponseData;
import com.qp.grocery_booking.exception.GroceryOutOfStockException;
import com.qp.grocery_booking.exception.UserNotFoundException;
import com.qp.grocery_booking.model.*;
import com.qp.grocery_booking.repository.GroceryRepository;
import com.qp.grocery_booking.repository.OrderRepository;
import com.qp.grocery_booking.repository.UserGroceryTnxRepository;
import com.qp.grocery_booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserGroceryTnxServiceImpl implements UserGroceryTnxService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroceryRepository groceryRepository;

    @Autowired
    UserGroceryTnxRepository userGroceryTnxRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    GroceryServiceImpl groceryService;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseData bookGroceries(Long userId, List<Long> ids) {
        try {
            // Get user
            UserModel user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));

            double totalOrderAmount = 0.0;
            List<UserGroceryTnxModel> userGroceryTnxs = new ArrayList<>();
            List<GroceryModel> updatedGroceries = new ArrayList<>();
            List<Long> failedItems = new ArrayList<>();

            for (Long groceryId : ids) {
                Optional<GroceryModel> optionalGrocery = groceryRepository.findById(groceryId);

                if (optionalGrocery.isEmpty() || optionalGrocery.get().getQuantity() <= 0) {
                    failedItems.add(groceryId); // Add to failed list
                    continue; // Skip this item
                }

                GroceryModel grocery = optionalGrocery.get();

                // Deduct quantity
                grocery.setQuantity(grocery.getQuantity() - 1);
                updatedGroceries.add(grocery);

                // Prepare transaction model
                UserGroceryTnxModel userGroceryTnxModel = new UserGroceryTnxModel();
                userGroceryTnxModel.setUser(user);
                userGroceryTnxModel.setGrocery(grocery);
                userGroceryTnxModel.setAmount(grocery.getPrice());
                totalOrderAmount += grocery.getPrice();
                userGroceryTnxs.add(userGroceryTnxModel);
            }

            // Save only if there are successful transactions
            if (!userGroceryTnxs.isEmpty()) {
                groceryRepository.saveAll(updatedGroceries);
                List<UserGroceryTnxModel> savedTnxs = userGroceryTnxRepository.saveAll(userGroceryTnxs);

                // Create order
                OrderModel orderModel = new OrderModel();
                orderModel.setUserGroceryTnxModel(savedTnxs);
                orderModel.setTotalAmount(totalOrderAmount);
                orderModel.setOrderStatus(OrderStatus.PAID);

                OrderModel result = orderRepository.save(orderModel);

                return ResponseData.builder()
                        .status(HttpStatus.OK)
                        .data(OrderResponse.builder()
                                .id(result.getId())
                                .failedItems(failedItems) // Include failed items
                                .build())
                        .message(ResponseMessages.ORDER_BOOKED_SUCCESSFULLY.getValue())
                        .build();
            }

            // If all items failed, return an appropriate response
            return ResponseData.builder()
                    .status(HttpStatus.PARTIAL_CONTENT)
                    .data(Collections.emptyMap()) // No successful transactions
                    .message("No items were available to book. Failed items: " + failedItems)
                    .build();

        } catch (UserNotFoundException e) {
            return ResponseData.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(e.getMessage())
                    .build();
        }
    }


    @Override
    public ResponseData getGroceries() {
        return groceryService.getGroceryItemsWithQuantity();
    }
}
