package com.example.qp_assessment.grocery.repository;

import com.example.qp_assessment.grocery.model.enitities.GroceryItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroceryItemsRepository extends JpaRepository<GroceryItems, Long> {
    Optional<GroceryItems> findByName(String name);
    Optional<List<GroceryItems>> findByInventoryLevelGreaterThan(int level);

}
