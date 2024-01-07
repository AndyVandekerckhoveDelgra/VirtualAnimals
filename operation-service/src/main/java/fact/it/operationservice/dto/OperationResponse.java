package fact.it.operationservice.dto;

import fact.it.operationservice.model.FeedingTimeItem;
import fact.it.operationservice.model.OrderLineItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationResponse {
    private String operationNumber;
    private LocalDateTime date;
    private List<OrderLineItemDto> orderLineItemsList;
    private List<FeedingTimeItemDto> feedingTimeItemsList;

}
