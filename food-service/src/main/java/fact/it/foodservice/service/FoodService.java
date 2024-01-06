package fact.it.foodservice.service;

import fact.it.foodservice.dto.FoodRequest;
import fact.it.foodservice.dto.FoodResponse;
import fact.it.foodservice.model.Food;
import fact.it.foodservice.repository.FoodRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    public List<FoodResponse> getAllAnimalsByFoodCode(List<String> foodcode) {
        List<Food> animals = foodRepository.findByFoodcodeIn(foodcode);

        return animals.stream().map(this::mapToFoodResponse).toList();
    }
    private FoodResponse mapToFoodResponse(Food food) {
        return FoodResponse.builder()
                .name(food.getName())
                .foodcode(food.getFoodcode())  // Check if quantity is not zero
                .build();
    }

    public List<FoodResponse> getAllFoods() {
        List<Food> adoptedAnimals = foodRepository.findAll();

        return adoptedAnimals.stream().map(this::mapToFoodResponse).toList();
    }

    public void createFood(FoodRequest foodRequest){
        Food food = Food.builder()
                .foodcode(foodRequest.getFoodcode())
                .name(foodRequest.getName())
                .build();

        foodRepository.save(food);
    }

    public boolean deleteFoodItemsByFoodcode(String foodcode) {
        List<Food> itemsToDelete = foodRepository.findByFoodcode(foodcode);
        if (!itemsToDelete.isEmpty()) {
            foodRepository.deleteAll(itemsToDelete);
            return true;
        } else {
            return false;
        }
    }

    public Food updateFoodByFoodcode(Food updateFood, String skuCode) {
        List<Food> foodOptional = foodRepository.findByFoodcode(skuCode);
        if (!foodOptional.isEmpty()){
            Food food = foodOptional.get(0);
            food.setFoodcode(updateFood.getFoodcode());
            food.setName(updateFood.getName());
            return foodRepository.save(food);
        }
        return null;
    }


    public List<FoodResponse> findByKeywordInName(String keyword) {
        List<Food> animals = foodRepository.findByKeywordInName(keyword);

        return animals.stream().map(this::mapToFoodResponse).toList();

    }
}
