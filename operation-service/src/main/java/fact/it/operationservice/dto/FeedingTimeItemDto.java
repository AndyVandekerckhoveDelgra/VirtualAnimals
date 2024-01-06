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
public class FeedingTimeItemDto {
    private Long id;
    private String skuCode;
    private String foodcode;
    private String name;
    private String nickname;
}
