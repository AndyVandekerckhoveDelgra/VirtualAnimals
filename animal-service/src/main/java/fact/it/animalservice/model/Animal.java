package fact.it.animalservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "animal")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Animal {
    private String id;
    private String skuCode;
    private String name;
    private String description;
    private BigDecimal price;
}
