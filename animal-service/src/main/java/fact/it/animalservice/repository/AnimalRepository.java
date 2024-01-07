package fact.it.animalservice.repository;

import fact.it.animalservice.model.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends MongoRepository<Animal, String> {
    List<Animal> findByAnimalCodeIn(List<String> skuCode);
    List<Animal> findByAnimalCode(String skuCode);


    @Query("{'$or':[ {'name': {$regex : ?0, $options: 'i'}}, {'description': {$regex : ?0, $options: 'i'}} ]}")
    List<Animal> findByKeywordInNameOrDescription(String keyword);

}
