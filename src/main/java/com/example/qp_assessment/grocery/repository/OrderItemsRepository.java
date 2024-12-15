package com.example.qp_assessment.grocery.repository;

import com.example.qp_assessment.grocery.model.enitities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems,Long> {
}
