package com.example.qp_assessment.grocery.service.impl;

import com.example.qp_assessment.grocery.model.AdjustInventoryLevelRequest;
import com.example.qp_assessment.grocery.model.enitities.GroceryItems;
import com.example.qp_assessment.grocery.repository.GroceryItemsRepository;
import com.example.qp_assessment.grocery.service.AddGroceryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AddGrocerItemServiceImpl implements AddGroceryService {

    @Autowired
    GroceryItemsRepository groceryItemsRepository;

    @Override
    public GroceryItems addGroceryItems(GroceryItems item) {
        Optional<GroceryItems> existingItems = groceryItemsRepository.findByName(item.getName());
        if (existingItems.isPresent()){
            GroceryItems groceryItems = existingItems.get();
                   groceryItems.setInventoryLevel(item.getInventoryLevel()+ groceryItems.getInventoryLevel());
            return groceryItemsRepository.save(groceryItems);
        }
        else {
            return groceryItemsRepository.save(item);
        }

    }

    @Override
    public List<GroceryItems> allGroceryItems() {
       List<GroceryItems> gc =  groceryItemsRepository.findAll();
       // GroceryItems gc = GroceryItems.builder().id(1L).name("abc").price(111.0).inventoryLevel(2).build();
        return gc;
    }

    @Override
    public void removeGroceryItems(Long id) {
        if (groceryItemsRepository.existsById(id)){
            groceryItemsRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("Grocery Item with ID: "+ id+ " doesnt exists");
        }
    }

    @Override
    public GroceryItems updateGroceryItems(GroceryItems item, Long id) {
        return groceryItemsRepository.findById(id).map(
                existingItem ->{
                    Optional.ofNullable(item.getName()).ifPresent(existingItem::setName);
                    Optional.ofNullable(item.getPrice()).ifPresent(existingItem::setPrice);
                    Optional.ofNullable(item.getInventoryLevel()).ifPresent(existingItem::setInventoryLevel);
                    return groceryItemsRepository.save(existingItem);
                }).orElseThrow(
                () -> new RuntimeException("Didnt find the item with id: "+id));
    }

    @Override
    public GroceryItems adjustInventoryLevel(AdjustInventoryLevelRequest adjustInventoryLevelRequest) {
        GroceryItems groceryItems =  groceryItemsRepository.findById(adjustInventoryLevelRequest.getId())
                .orElseThrow(() -> new RuntimeException("Didnt find the item with id: "+adjustInventoryLevelRequest.getId()));
        if (adjustInventoryLevelRequest.getOp().equalsIgnoreCase("ADD")){
            groceryItems.setInventoryLevel(groceryItems.getInventoryLevel() + adjustInventoryLevelRequest.getQuantity());
        }
        else if (adjustInventoryLevelRequest.getOp().equalsIgnoreCase("REDUCE")){
            if (groceryItems.getInventoryLevel()< adjustInventoryLevelRequest.getQuantity()){
                throw new RuntimeException("Not sufficient level to reduce ");
            }
            groceryItems.setInventoryLevel(groceryItems.getInventoryLevel() - adjustInventoryLevelRequest.getQuantity());

        }
        else {
            throw new IllegalArgumentException("Use ADD or REDUCE to change the inventory level");
        }
return         groceryItemsRepository.save(groceryItems);

    }


}
