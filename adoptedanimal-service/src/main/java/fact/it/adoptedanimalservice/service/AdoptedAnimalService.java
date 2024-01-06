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
/*
    @PostConstruct
    public void loadData() {
        if (adoptedAnimalRepository.count() > 0) {
            AdoptedAnimal adoptedanimal = new AdoptedAnimal();
            adoptedanimal.setSkuCode("tube6in");
            adoptedanimal.setQuantity(100);

            AdoptedAnimal adoptedanimal1 = new AdoptedAnimal();
            adoptedanimal1.setSkuCode("beam10ft");
            adoptedanimal1.setQuantity(0);

            adoptedAnimalRepository.save(adoptedanimal);
            adoptedAnimalRepository.save(adoptedanimal1);
        }
    }*/


    public List<AdoptedAnimalResponse> getAllAnimalsBySkuCode(List<String> skuCode) {
        List<AdoptedAnimal> animals = adoptedAnimalRepository.findBySkuCodeIn(skuCode);

        return animals.stream().map(this::mapToAdoptedAnimalResponse).toList();
    }

    public void createAdoptedAnimal(AdoptedAnimalRequest adoptedAnimalRequest){
        AdoptedAnimal adoptedAnimal = AdoptedAnimal.builder()
                .skuCode(adoptedAnimalRequest.getSkuCode())
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
                .skuCode(adoptedAnimal.getSkuCode())
                .nickname(adoptedAnimal.getNickname())  // Check if quantity is not zero
                .build();
    }

    public boolean deleteAdoptedAnimalItemsBySkuCode(String skuCode) {
        List<AdoptedAnimal> itemsToDelete = adoptedAnimalRepository.findBySkuCode(skuCode);
        if (!itemsToDelete.isEmpty()) {
            adoptedAnimalRepository.deleteAll(itemsToDelete);
            return true;
        } else {
            return false;
        }
    }

    public AdoptedAnimal updateAnimalBySkuCode(AdoptedAnimal updateAnimal, String skuCode) {
        List<AdoptedAnimal> animalOptional = adoptedAnimalRepository.findBySkuCode(skuCode);
        if (!animalOptional.isEmpty()){
            AdoptedAnimal animal = animalOptional.get(0);
            animal.setNickname(updateAnimal.getNickname());
            animal.setSkuCode(updateAnimal.getSkuCode());
            return adoptedAnimalRepository.save(animal);
        }
        return null;
    }

    public List<AdoptedAnimalResponse> findByKeywordInNickName(String keyword) {
        List<AdoptedAnimal> animals = adoptedAnimalRepository.findByKeywordInNickName(keyword);

        return animals.stream().map(this::mapToAdoptedAnimalResponse).toList();
    }



}
