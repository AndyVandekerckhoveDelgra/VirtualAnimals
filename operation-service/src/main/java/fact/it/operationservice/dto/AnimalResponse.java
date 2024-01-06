package fact.it.operationservice.dto;

        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {
    private String id;
    private String skuCode;
    private String name;
    private String description;
    private BigDecimal price;
}