package com.binaryextractcorp.foodcatalogue.repo;

import com.binaryextractcorp.foodcatalogue.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem, Integer> {
}
