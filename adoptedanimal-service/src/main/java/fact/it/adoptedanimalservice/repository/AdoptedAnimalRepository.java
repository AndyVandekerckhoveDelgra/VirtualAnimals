package fact.it.adoptedanimalservice.repository;

import fact.it.adoptedanimalservice.model.AdoptedAnimal;
        import jakarta.transaction.Transactional;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
@Transactional
public interface AdoptedAnimalRepository extends JpaRepository<AdoptedAnimal, Long> {
    List<AdoptedAnimal> findBySkuCodeIn(List<String> skuCode);
        List<AdoptedAnimal> findBySkuCode(String skuCode);
    @Query("SELECT a FROM AdoptedAnimal a WHERE LOWER(a.nickname) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<AdoptedAnimal> findByKeywordInNickName(String keyword);
}
