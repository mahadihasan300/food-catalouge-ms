package com.binaryextractcorp.foodcatalogue.service;

import com.binaryextractcorp.foodcatalogue.dto.FoodItemDTO;
import com.binaryextractcorp.foodcatalogue.entity.FoodItem;
import com.binaryextractcorp.foodcatalogue.mapper.FoodItemMapper;
import com.binaryextractcorp.foodcatalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodCatalogueService {

    @Autowired
    FoodItemRepo foodItemRepo;

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem foodItemSavedInDb = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(foodItemSavedInDb);
    }
}
