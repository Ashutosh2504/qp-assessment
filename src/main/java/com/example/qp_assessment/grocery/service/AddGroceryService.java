package com.example.qp_assessment.grocery.service;

import com.example.qp_assessment.grocery.model.AdjustInventoryLevelRequest;
import com.example.qp_assessment.grocery.model.enitities.GroceryItems;

import java.util.List;



public interface AddGroceryService {
    GroceryItems addGroceryItems(GroceryItems item);

    List<GroceryItems> allGroceryItems();

    void removeGroceryItems(Long id);

    GroceryItems updateGroceryItems(GroceryItems item, Long id);

    GroceryItems adjustInventoryLevel(AdjustInventoryLevelRequest adjustInventoryLevelRequest);

}
