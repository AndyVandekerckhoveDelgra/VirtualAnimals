package fact.it.foodservice.repository;

import fact.it.foodservice.model.Food;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByFoodcodeIn(List<String> foodcode);
    List<Food> findByFoodcode(String foodcode);
    @Query("SELECT a FROM Food a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Food> findByKeywordInName(String keyword);
}
