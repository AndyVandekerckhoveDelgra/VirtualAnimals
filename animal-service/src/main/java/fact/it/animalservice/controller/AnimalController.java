package fact.it.animalservice.controller;

import fact.it.animalservice.dto.AnimalRequest;
import fact.it.animalservice.dto.AnimalResponse;
import fact.it.animalservice.model.Animal;
import fact.it.animalservice.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animal")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createAnimal
            (@RequestBody AnimalRequest animalRequest) {
        animalService.createAnimal(animalRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalResponse> getAllAnimalsBySkuCode
            (@RequestParam List<String> skuCode) {
        return animalService.getAllAnimalsBySkuCode(skuCode);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalResponse> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/search/{keyword}")
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalResponse> searchAnimalsByNameOrDescription(@PathVariable String keyword) {
        return animalService.findByKeywordInNameOrDescription(keyword);
    }



    @DeleteMapping("/delete/{skuCode}")
    public ResponseEntity<String> deleteInventoryItemsBySkuCode(@PathVariable String skuCode) {
        boolean deleted = animalService.deleteAnimalItemsBySkuCode(skuCode);
        if (deleted) {
            return ResponseEntity.ok("Inventory items deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventory items not found");
        }
    }

    @PutMapping("/update/{skuCode}")
    public ResponseEntity<Animal> updateProduct(@RequestBody Animal updateAnimal, @PathVariable("skuCode") String skuCode){
        Animal animal = animalService.updateAnimalBySkuCode(updateAnimal, skuCode);
        if (animal==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

}