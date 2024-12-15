package com.example.qp_assessment.grocery.controller;

import com.example.qp_assessment.grocery.model.OrderItemRequests;
import com.example.qp_assessment.grocery.model.enitities.GroceryItems;
import com.example.qp_assessment.grocery.model.enitities.Orders;
import com.example.qp_assessment.grocery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/grocery")
public class GroceryUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/items") //retrieving only items with inventory level greater than 0
    public ResponseEntity<List<GroceryItems>> getAvailableGroceryItems() {
        return ResponseEntity.ok(userService.allGroceryItems());
    }


    @PostMapping("/book")
    public ResponseEntity<Orders> bookOrder(@RequestParam String customerName, @RequestBody List<OrderItemRequests> orderItemRequests) {
        try {
            return ResponseEntity.ok(userService.bookOrder(customerName, orderItemRequests));

        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
