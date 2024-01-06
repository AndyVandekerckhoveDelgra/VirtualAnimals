package fact.it.animalservice.service;

import fact.it.animalservice.dto.AnimalRequest;
import fact.it.animalservice.dto.AnimalResponse;
import fact.it.animalservice.model.Animal;
import fact.it.animalservice.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    public void createAnimal(AnimalRequest animalRequest){
        Animal animal = Animal.builder()
                .skuCode(animalRequest.getSkuCode())
                .name(animalRequest.getName())
                .description(animalRequest.getDescription())
                .price(animalRequest.getPrice())
                .build();

        animalRepository.save(animal);
    }


    public List<AnimalResponse> getAllAnimals() {
        List<Animal> animals = animalRepository.findAll();

        return animals.stream().map(this::mapToAnimalResponse).toList();
    }

    public List<AnimalResponse> getAllAnimalsBySkuCode(List<String> skuCode) {
        List<Animal> animals = animalRepository.findBySkuCodeIn(skuCode);

        return animals.stream().map(this::mapToAnimalResponse).toList();
    }

    public List<AnimalResponse> getAllAnimalsByName(List<String> name) {
        List<Animal> animals = animalRepository.findByNameContaining(name);

        return animals.stream().map(this::mapToAnimalResponse).toList();
    }

    private AnimalResponse mapToAnimalResponse(Animal animal) {
        return AnimalResponse.builder()
                .id(animal.getId())
                .skuCode(animal.getSkuCode())
                .name(animal.getName())
                .description(animal.getDescription())
                .price(animal.getPrice())
                .build();
    }

    public boolean deleteAnimalItemsBySkuCode(String skuCode) {
        List<Animal> itemsToDelete = animalRepository.findBySkuCode(skuCode);
        if (!itemsToDelete.isEmpty()) {
            animalRepository.deleteAll(itemsToDelete);
            return true;
        } else {
            return false;
        }
    }


    public Animal updateAnimalBySkuCode(Animal updateAnimal, String skuCode) {
        List<Animal> animalOptional = animalRepository.findBySkuCode(skuCode);
        if (!animalOptional.isEmpty()){
            Animal animal = animalOptional.get(0);
            animal.setName(updateAnimal.getName());
            animal.setDescription(updateAnimal.getDescription());
            animal.setPrice(updateAnimal.getPrice());
            animal.setSkuCode(updateAnimal.getSkuCode());
            return animalRepository.save(animal);
        }
        return null;
    }

    public List<AnimalResponse> findByKeywordInNameOrDescription(String keyword) {
        List<Animal> animals = animalRepository.findByKeywordInNameOrDescription(keyword);

        return animals.stream().map(this::mapToAnimalResponse).toList();
    }

}