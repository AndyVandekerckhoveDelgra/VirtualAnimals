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
    public List<AnimalResponse> getAllAnimalsByAnimalCode
            (@RequestParam List<String> animalCode) {
        return animalService.getAllAnimalsByAnimalCode(animalCode);
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



    @DeleteMapping("/delete/{animalCode}")
    public ResponseEntity<String> deleteInventoryItemsByAnimalCode(@PathVariable String animalCode) {
        boolean deleted = animalService.deleteAnimalItemsByAnimalCode(animalCode);
        if (deleted) {
            return ResponseEntity.ok("Animal items deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal items not found");
        }
    }

    @PutMapping("/update/{animalCode}")
    public ResponseEntity<Animal> updateAnimal(@RequestBody Animal updateAnimal, @PathVariable("animalCode") String animalCode){
        Animal animal = animalService.updateAnimalByAnimalCode(updateAnimal, animalCode);
        if (animal==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }


}