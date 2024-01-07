package fact.it.adoptedanimalservice.controller;

import fact.it.adoptedanimalservice.dto.AdoptedAnimalResponse;
import fact.it.adoptedanimalservice.dto.AdoptedAnimalRequest;
import fact.it.adoptedanimalservice.model.AdoptedAnimal;
import fact.it.adoptedanimalservice.service.AdoptedAnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adoptedanimal")
@RequiredArgsConstructor
public class AdoptedAnimalController {

    private final AdoptedAnimalService adoptedAnimalService;

    // http://localhost:8082/api/adoptedanimal?animalCode=tube6in&animalCode=ABC123
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AdoptedAnimalResponse> getAdoptedAnimalByAnimalCode
            (@RequestParam List<String> animalCode) {
        return adoptedAnimalService.getAllAnimalsByAnimalCode(animalCode);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<AdoptedAnimalResponse> getAllAdoptedanimals() {
        return adoptedAnimalService.getAllAdoptedAnimals();
    }


    @GetMapping("/search/{keyword}")
    @ResponseStatus(HttpStatus.OK)
    public List<AdoptedAnimalResponse> searchAnimalsByName(@PathVariable String keyword) {
        return adoptedAnimalService.findByKeywordInNickName(keyword);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createAdoptedAnimal(@RequestBody AdoptedAnimalRequest productRequest) {
        adoptedAnimalService.createAdoptedAnimal(productRequest);
        String responseMessage = "Adopted Animal created successfully.";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @DeleteMapping("/delete/{animalCode}")
    public ResponseEntity<String> deleteAdoptedAnimalItemsByAnimalCode(@PathVariable String animalCode) {
        boolean deleted = adoptedAnimalService.deleteAdoptedAnimalItemsByAnimalCode(animalCode);
        if (deleted) {
            return ResponseEntity.ok("Adoptedanimal items deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Adoptedanimal items not found");
        }
    }



    @PutMapping("/update/{animalCode}")
    public ResponseEntity<AdoptedAnimal> updateAdoptedAnimal(@RequestBody AdoptedAnimal updateAnimal, @PathVariable("animalCode") String animalCode){
        AdoptedAnimal animal = adoptedAnimalService.updateAnimalByAnimalCode(updateAnimal, animalCode);
        if (animal==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

}
