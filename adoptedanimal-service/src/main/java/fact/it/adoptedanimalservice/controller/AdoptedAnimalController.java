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

    // http://localhost:8082/api/adoptedanimal?skuCode=tube6in&skuCode=beam10ft
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AdoptedAnimalResponse> getAdoptedAnimalBySkuCode
            (@RequestParam List<String> skuCode) {
        return adoptedAnimalService.getAllAnimalsBySkuCode(skuCode);
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
    @ResponseStatus(HttpStatus.CREATED) // Use HttpStatus.CREATED for successful resource creation
    public ResponseEntity<String> createProduct(@RequestBody AdoptedAnimalRequest productRequest) {
        adoptedAnimalService.createAdoptedAnimal(productRequest);

        // Assuming the product creation was successful, return a success message
        String responseMessage = "Product created successfully.";

        // You can also include additional information in the response if needed

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @DeleteMapping("/delete/{skuCode}")
    public ResponseEntity<String> deleteAdoptedAnimalItemsBySkuCode(@PathVariable String skuCode) {
        boolean deleted = adoptedAnimalService.deleteAdoptedAnimalItemsBySkuCode(skuCode);
        if (deleted) {
            return ResponseEntity.ok("Adoptedanimal items deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Adoptedanimal items not found");
        }
    }



    @PutMapping("/update/{skuCode}")
    public ResponseEntity<AdoptedAnimal> updateProduct(@RequestBody AdoptedAnimal updateAnimal, @PathVariable("skuCode") String skuCode){
        AdoptedAnimal animal = adoptedAnimalService.updateAnimalBySkuCode(updateAnimal, skuCode);
        if (animal==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

}
