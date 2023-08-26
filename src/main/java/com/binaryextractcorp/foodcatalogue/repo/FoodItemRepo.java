package com.binaryextractcorp.foodcatalogue.repo;

import com.binaryextractcorp.foodcatalogue.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem, Integer> {
    List findByRestaurantId(Integer restaurantId);
}
