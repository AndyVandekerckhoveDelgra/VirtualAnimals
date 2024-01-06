package fact.it.operationservice.dto;

import fact.it.operationservice.model.FeedingTimeItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationRequest {
    private List<OrderLineItemDto> orderLineItemsDtoList;
    private List<FeedingTimeItemDto> feedingTimeItemsDtoList;
}
