package com.example.qp_assessment.grocery.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class AdjustInventoryLevelRequest {
    private Long id;

    @NonNull
    private Integer quantity;


    private String op;

}
