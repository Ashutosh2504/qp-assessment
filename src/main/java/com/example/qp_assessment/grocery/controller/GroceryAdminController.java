package com.example.qp_assessment.grocery.controller;

import com.example.qp_assessment.grocery.model.AdjustInventoryLevelRequest;
import com.example.qp_assessment.grocery.model.enitities.GroceryItems;
import com.example.qp_assessment.grocery.service.AddGroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/grocery")
public class GroceryAdminController {

    @Autowired
    private  AddGroceryService addGroceryService;

//    public GroceryAdminController(AddGroceryService addGroceryService) {
//        this.addGroceryService = addGroceryService;
//    }


    @PostMapping("/post")
    public ResponseEntity<GroceryItems> addGroceryItem(@RequestBody GroceryItems groceryItems) {
        GroceryItems storedGroceryItem = addGroceryService.addGroceryItems(groceryItems);
        return ResponseEntity.status(HttpStatus.CREATED).body(storedGroceryItem);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GroceryItems>> getGroceryItemsList() {
        List<GroceryItems> storedGroceryItemList = addGroceryService.allGroceryItems();
        return ResponseEntity.ok(storedGroceryItemList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroceryItems> updateGroceryItem(@PathVariable Long id, @RequestBody GroceryItems updatedItem) {
        try {
            GroceryItems item = addGroceryService.updateGroceryItems(updatedItem, id);
            return ResponseEntity.ok(item);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeGroceryItemById(@PathVariable Long id) {
        try{
            addGroceryService.removeGroceryItems(id);
            return ResponseEntity.ok("The Grocery Item has been removed with id: "+id);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Grocery Item  with id: "+id+"is not available");
        }

       }

       @PostMapping("/adjust")
    public ResponseEntity<GroceryItems> adjustInventoryLevel(@RequestBody AdjustInventoryLevelRequest request){
        try{
            GroceryItems items = addGroceryService.adjustInventoryLevel(request);
            return ResponseEntity.ok(items);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
       }



}
