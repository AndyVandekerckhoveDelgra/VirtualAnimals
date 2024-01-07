package fact.it.adoptedanimalservice.service;

import fact.it.adoptedanimalservice.dto.AdoptedAnimalRequest;
import fact.it.adoptedanimalservice.dto.AdoptedAnimalResponse;
import fact.it.adoptedanimalservice.model.AdoptedAnimal;
import fact.it.adoptedanimalservice.repository.AdoptedAnimalRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptedAnimalService {

    private final AdoptedAnimalRepository adoptedAnimalRepository;


    public List<AdoptedAnimalResponse> getAllAnimalsByAnimalCode(List<String> animalCode) {
        List<AdoptedAnimal> animals = adoptedAnimalRepository.findByAnimalCodeIn(animalCode);

        return animals.stream().map(this::mapToAdoptedAnimalResponse).toList();
    }

    public void createAdoptedAnimal(AdoptedAnimalRequest adoptedAnimalRequest){
        AdoptedAnimal adoptedAnimal = AdoptedAnimal.builder()
                .animalCode(adoptedAnimalRequest.getAnimalCode())
                .nickname(adoptedAnimalRequest.getNickname())
                .build();

        adoptedAnimalRepository.save(adoptedAnimal);
    }


    public List<AdoptedAnimalResponse> getAllAdoptedAnimals() {
        List<AdoptedAnimal> adoptedAnimals = adoptedAnimalRepository.findAll();

        return adoptedAnimals.stream().map(this::mapToAdoptedAnimalResponse).toList();
    }

    private AdoptedAnimalResponse mapToAdoptedAnimalResponse(AdoptedAnimal adoptedAnimal) {
        return AdoptedAnimalResponse.builder()
                .animalCode(adoptedAnimal.getAnimalCode())
                .nickname(adoptedAnimal.getNickname())
                .build();
    }

    public boolean deleteAdoptedAnimalItemsByAnimalCode(String animalCode) {
        List<AdoptedAnimal> itemsToDelete = adoptedAnimalRepository.findByAnimalCode(animalCode);
        if (!itemsToDelete.isEmpty()) {
            adoptedAnimalRepository.deleteAll(itemsToDelete);
            return true;
        } else {
            return false;
        }
    }

    public AdoptedAnimal updateAnimalByAnimalCode(AdoptedAnimal updateAnimal, String animalCode) {
        List<AdoptedAnimal> animalOptional = adoptedAnimalRepository.findByAnimalCode(animalCode);
        if (!animalOptional.isEmpty()){
            AdoptedAnimal animal = animalOptional.get(0);
            animal.setNickname(updateAnimal.getNickname());
            animal.setAnimalCode(updateAnimal.getAnimalCode());
            return adoptedAnimalRepository.save(animal);
        }
        return null;
    }

    public List<AdoptedAnimalResponse> findByKeywordInNickName(String keyword) {
        List<AdoptedAnimal> animals = adoptedAnimalRepository.findByKeywordInNickName(keyword);

        return animals.stream().map(this::mapToAdoptedAnimalResponse).toList();
    }



}
