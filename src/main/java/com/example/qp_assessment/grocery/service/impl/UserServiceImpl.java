package com.example.qp_assessment.grocery.service.impl;

import com.example.qp_assessment.grocery.model.OrderItemRequests;
import com.example.qp_assessment.grocery.model.enitities.GroceryItems;
import com.example.qp_assessment.grocery.model.enitities.Orders;
import com.example.qp_assessment.grocery.model.enitities.OrderItems;
import com.example.qp_assessment.grocery.repository.GroceryItemsRepository;
import com.example.qp_assessment.grocery.repository.OrderItemsRepository;
import com.example.qp_assessment.grocery.repository.OrderRepository;
import com.example.qp_assessment.grocery.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    GroceryItemsRepository groceryItemsRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;


    @Override
    public List<GroceryItems> allGroceryItems() {
        Optional<List<GroceryItems>> optionalGroceryItems = groceryItemsRepository.findByInventoryLevelGreaterThan(0);
        return optionalGroceryItems.orElse(null);
    }

    @Override
    public Orders bookOrder(String customerName, List<OrderItemRequests> orderItemRequests) {
        Orders orders = new Orders();
        orders.setCustomerName(customerName);
        orders = orderRepository.save(orders);


        for (OrderItemRequests or : orderItemRequests) {
            GroceryItems groceryItems = groceryItemsRepository.findById(or.getItemId()).orElseThrow(
                    () -> new RuntimeException("Item not found with ID: " + or.getItemId()));
            if (groceryItems.getInventoryLevel() < or.getQuantity()) {
                throw new RuntimeException("Insufficient inventory for item: " + groceryItems.getName());
            }

            groceryItems.setInventoryLevel(groceryItems.getInventoryLevel() - or.getQuantity());
            groceryItemsRepository.save(groceryItems);
            OrderItems orderItem = OrderItems.builder()
                    .orders(orders)
                    .groceryItem(groceryItems)
                    .quantity(or.getQuantity())
                    .build();
            orderItemsRepository.save(orderItem);

        }


        return orders;
    }
}