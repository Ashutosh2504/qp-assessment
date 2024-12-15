package com.example.qp_assessment.grocery.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemRequests {
    private Long itemId;
    private Integer quantity;

}
