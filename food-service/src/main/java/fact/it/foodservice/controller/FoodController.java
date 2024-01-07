package fact.it.foodservice.controller;

import fact.it.foodservice.dto.FoodRequest;
import fact.it.foodservice.dto.FoodResponse;
import fact.it.foodservice.model.Food;
import fact.it.foodservice.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    // http://localhost:8082/api/food?foodcode=DFG888
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FoodResponse> getFoodByFoodcode
    (@RequestParam List<String> foodcode) {
        return foodService.getAllAnimalsByFoodCode(foodcode);
    }


    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodResponse> getAllFoods() {
        return foodService.getAllFoods();
    }


    @GetMapping("/search/{keyword}")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodResponse> searchAnimalsByName(@PathVariable String keyword) {
        return foodService.findByKeywordInName(keyword);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createFood(@RequestBody FoodRequest foodRequest) {
        foodService.createFood(foodRequest);
        String responseMessage = "Food item created successfully.";

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @DeleteMapping("/delete/{foodcode}")
    public ResponseEntity<String> deleteAdoptedAnimalItemsBySkuCode(@PathVariable String foodcode) {
        boolean deleted = foodService.deleteFoodItemsByFoodcode(foodcode);
        if (deleted) {
            return ResponseEntity.ok("Food items deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food items not found");
        }
    }

    @PutMapping("/update/{foodcode}")
    public ResponseEntity<Food> updateFood(@RequestBody Food updateFood, @PathVariable("foodcode") String foodcode){
        Food food = foodService.updateFoodByFoodcode(updateFood, foodcode);
        if (food==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
