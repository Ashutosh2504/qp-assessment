package com.example.qp_assessment.grocery.service;

import com.example.qp_assessment.grocery.model.OrderItemRequests;
import com.example.qp_assessment.grocery.model.enitities.GroceryItems;
import com.example.qp_assessment.grocery.model.enitities.Orders;

import java.util.List;


public interface UserService {

    List<GroceryItems> allGroceryItems();

    Orders bookOrder(String customerName, List<OrderItemRequests> orderItemRequests);
}
