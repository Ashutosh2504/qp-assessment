package com.example.qp_assessment.grocery.repository;

import com.example.qp_assessment.grocery.model.enitities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
