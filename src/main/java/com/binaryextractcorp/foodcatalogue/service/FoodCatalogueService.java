package com.binaryextractcorp.foodcatalogue.service;

import com.binaryextractcorp.foodcatalogue.dto.FoodCataloguePage;
import com.binaryextractcorp.foodcatalogue.dto.FoodItemDTO;
import com.binaryextractcorp.foodcatalogue.dto.Restaurant;
import com.binaryextractcorp.foodcatalogue.entity.FoodItem;
import com.binaryextractcorp.foodcatalogue.mapper.FoodItemMapper;
import com.binaryextractcorp.foodcatalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {

    @Autowired
    FoodItemRepo foodItemRepo;

    @Autowired
    RestTemplate restTemplate;

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem foodItemSavedInDb = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(foodItemSavedInDb);
    }

    public FoodCataloguePage fetchFoodCatalougePageDetails(Integer restaurantId) {

        // food item list
        List<FoodItem> foodItemList =  fetchFoodItemList(restaurantId);

        // restaurant details from another ms
        Restaurant restaurant =  fetchRestaurantDetailsFromRestaurantMs(restaurantId);

        // merge those two in one response and return
        return createFoodCataloguePage(foodItemList,restaurant);

    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {

        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemList(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);

        return foodCataloguePage;
    }

    private Restaurant fetchRestaurantDetailsFromRestaurantMs(Integer restaurantId) {
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchRestaurantById/" + restaurantId,Restaurant.class);

    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }
}
