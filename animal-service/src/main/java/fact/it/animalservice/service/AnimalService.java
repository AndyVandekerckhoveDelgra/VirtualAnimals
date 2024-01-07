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
                .animalCode(animalRequest.getAnimalCode())
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

    public List<AnimalResponse> getAllAnimalsByAnimalCode(List<String> animalCode) {
        List<Animal> animals = animalRepository.findByAnimalCodeIn(animalCode);

        return animals.stream().map(this::mapToAnimalResponse).toList();
    }

    private AnimalResponse mapToAnimalResponse(Animal animal) {
        return AnimalResponse.builder()
                .id(animal.getId())
                .animalCode(animal.getAnimalCode())
                .name(animal.getName())
                .description(animal.getDescription())
                .price(animal.getPrice())
                .build();
    }

    public boolean deleteAnimalItemsByAnimalCode(String animalCode) {
        List<Animal> itemsToDelete = animalRepository.findByAnimalCode(animalCode);
        if (!itemsToDelete.isEmpty()) {
            animalRepository.deleteAll(itemsToDelete);
            return true;
        } else {
            return false;
        }
    }


    public Animal updateAnimalByAnimalCode(Animal updateAnimal, String animalCode) {
        List<Animal> animalOptional = animalRepository.findByAnimalCode(animalCode);
        if (!animalOptional.isEmpty()){
            Animal animal = animalOptional.get(0);
            animal.setName(updateAnimal.getName());
            animal.setDescription(updateAnimal.getDescription());
            animal.setPrice(updateAnimal.getPrice());
            animal.setAnimalCode(updateAnimal.getAnimalCode());
            return animalRepository.save(animal);
        }
        return null;
    }

    public List<AnimalResponse> findByKeywordInNameOrDescription(String keyword) {
        List<Animal> animals = animalRepository.findByKeywordInNameOrDescription(keyword);

        return animals.stream().map(this::mapToAnimalResponse).toList();
    }

}