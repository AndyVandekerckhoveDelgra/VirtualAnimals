package fact.it.operationservice.service;


        import fact.it.operationservice.dto.*;
        import fact.it.operationservice.model.FeedingTimeItem;
        import fact.it.operationservice.model.Operation;
        import fact.it.operationservice.model.OrderLineItem;
        import fact.it.operationservice.repository.OperationRepository;
        import jakarta.transaction.Transactional;
        import lombok.RequiredArgsConstructor;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Service;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PutMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.reactive.function.client.WebClient;

        import java.util.Arrays;
        import java.util.List;
        import java.util.UUID;
        import java.util.stream.Collectors;
        import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Transactional
public class OperationService {

    private final OperationRepository operationRepository;
    private final WebClient webClient;

    public boolean placeOrder(OperationRequest operationRequest) {
        Operation operation = new Operation();
        operation.setOperationNumber(UUID.randomUUID().toString());

        operation.setDate(LocalDateTime.now());

        List<OrderLineItem> orderLineItems = operationRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToOrderLineItem)
                .toList();

        operation.setOrderLineItemsList(orderLineItems);

        List<String> animalCodes = operation.getOrderLineItemsList().stream()
                .map(OrderLineItem::getAnimalCode)
                .toList();

        AdoptedAnimalResponse[] adoptedAnimalResponseArray = webClient.get()
                .uri("http://localhost:8082/api/adoptedanimal",
                        uriBuilder -> uriBuilder.queryParam("animalCode", animalCodes).build())
                .retrieve()
                .bodyToMono(AdoptedAnimalResponse[].class)
                .block();



            AnimalResponse[] animalResponseArray = webClient.get()
                    .uri("http://localhost:8080/api/animal",
                            uriBuilder -> uriBuilder.queryParam("animalCode", animalCodes).build())
                    .retrieve()
                    .bodyToMono(AnimalResponse[].class)
                    .block();

            operation.getOrderLineItemsList().stream()
                    .map(orderItem -> {
                        AnimalResponse animal = Arrays.stream(animalResponseArray)
                                .filter(p -> p.getAnimalCode().equals(orderItem.getAnimalCode()))
                                .findFirst()
                                .orElse(null);
                        if (animal != null) {
                            orderItem.setPrice(animal.getPrice());
                            orderItem.setName(animal.getName());
                        }
                        return orderItem;
                    })
                    .collect(Collectors.toList());

            operationRepository.save(operation);
            return true;
        }


    public boolean placeFeedingTime(OperationRequest operationRequest) {
        Operation operation = new Operation();
        operation.setOperationNumber(UUID.randomUUID().toString());
        operation.setDate(LocalDateTime.now());

        List<FeedingTimeItem> feedingTimeItems = operationRequest.getFeedingTimeItemsDtoList()
                .stream()
                .map(this::mapToFeedingTimeItem)
                .toList();

        operation.setFeedingTimeItemsList(feedingTimeItems);

        List<String> animalCodes = operation.getFeedingTimeItemsList().stream()
                .map(FeedingTimeItem::getAnimalCode)
                .toList();

        List<String> foodCodes = operation.getFeedingTimeItemsList().stream()
                .map(FeedingTimeItem::getFoodcode)
                .toList();

        AdoptedAnimalResponse[] adoptedAnimalResponseArray = webClient.get()
                .uri("http://localhost:8082/api/adoptedanimal",
                        uriBuilder -> uriBuilder.queryParam("animalCode", animalCodes).build())
                .retrieve()
                .bodyToMono(AdoptedAnimalResponse[].class)
                .block();

        FoodResponse[] foodResponseArray = webClient.get()
                .uri("http://localhost:8083/api/food",
                        uriBuilder -> uriBuilder.queryParam("foodcode", foodCodes).build())
                .retrieve()
                .bodyToMono(FoodResponse[].class)
                .block();

        operation.setFeedingTimeItemsList(operation.getFeedingTimeItemsList().stream()
                .map(orderItem -> {
                    AdoptedAnimalResponse adoptedAnimal = Arrays.stream(adoptedAnimalResponseArray)
                            .filter(p -> p.getAnimalCode().equals(orderItem.getAnimalCode()))
                            .findFirst()
                            .orElse(null);

                    FoodResponse food = Arrays.stream(foodResponseArray)
                            .filter(f -> f.getFoodcode().equals(orderItem.getFoodcode()))
                            .findFirst()
                            .orElse(null);

                    FeedingTimeItem orderLineItem = new FeedingTimeItem();
                    orderLineItem.setAnimalCode(orderItem.getAnimalCode());
                    orderLineItem.setFoodcode(orderItem.getFoodcode());

                    if (adoptedAnimal != null) {
                        orderLineItem.setNickname(adoptedAnimal.getNickname());
                    }

                    if (food != null) {
                        orderLineItem.setName(food.getName());
                    }

                    return orderLineItem;
                })
                .toList());

        operationRepository.save(operation);
        return true;
    }


    public List<OperationResponse> getAllOperations() {
        List<Operation> orders = operationRepository.findAll();

        return orders.stream()
                .map(order -> new OperationResponse(
                        order.getOperationNumber(),
                        order.getDate(),
                        mapToOrderLineItemsDto(order.getOrderLineItemsList()),
                        mapToFeedingTimeItemsDto(order.getFeedingTimeItemsList())
                ))
                .collect(Collectors.toList());
    }

    private OrderLineItem mapToOrderLineItem(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setNickname(orderLineItemDto.getNickname());
        orderLineItem.setAnimalCode(orderLineItemDto.getAnimalCode());
        return orderLineItem;
    }


    private FeedingTimeItem mapToFeedingTimeItem(FeedingTimeItemDto feedingTimeItemDto) {
        FeedingTimeItem feedingTimeItem = new FeedingTimeItem();
        feedingTimeItem.setName(feedingTimeItemDto.getName());
        feedingTimeItem.setNickname(feedingTimeItemDto.getNickname());
        feedingTimeItem.setFoodcode(feedingTimeItemDto.getFoodcode());
        feedingTimeItem.setAnimalCode(feedingTimeItemDto.getAnimalCode());
        return feedingTimeItem;
    }

    private List<OrderLineItemDto> mapToOrderLineItemsDto(List<OrderLineItem> orderLineItems) {
        return orderLineItems.stream()
                .map(orderLineItem -> new OrderLineItemDto(
                        orderLineItem.getId(),
                        orderLineItem.getAnimalCode(),
                        orderLineItem.getPrice(),
                        orderLineItem.getName(),
                        orderLineItem.getNickname()
                ))
                .collect(Collectors.toList());
    }
    private List<FeedingTimeItemDto> mapToFeedingTimeItemsDto(List<FeedingTimeItem> feedingTimeItems) {
        return feedingTimeItems.stream()
                .map(feedingTimeItem -> new FeedingTimeItemDto(
                        feedingTimeItem.getId(),
                        feedingTimeItem.getAnimalCode(),
                        feedingTimeItem.getFoodcode(),
                        feedingTimeItem.getName(),
                        feedingTimeItem.getNickname()
                ))
                .collect(Collectors.toList());
    }

    public List<OperationResponse> getAllOperationByOperationNumber(List<String> operationNumber) {
        List<Operation> animals = operationRepository.findByOperationNumberIn(operationNumber);

        return animals.stream().map(this::mapToOperationResponse).toList();
    }

    public List<OperationResponse> getOperationsAfterDate(LocalDateTime date) {
        List<Operation> operations = operationRepository.findByDateAfter(date);

        return operations.stream().map(this::mapToOperationResponse).toList();
    }

    private OperationResponse mapToOperationResponse(Operation operation) {
        List<OrderLineItemDto> orderLineItemDtoList = mapToOrderLineItemDtoList(operation.getOrderLineItemsList());
        List<FeedingTimeItemDto> feedingTimeItemDtoList = mapToFeedingTimeItemDtoList(operation.getFeedingTimeItemsList());

        return OperationResponse.builder()
                .operationNumber(operation.getOperationNumber())
                .date(operation.getDate())
                .orderLineItemsList(orderLineItemDtoList)
                .feedingTimeItemsList(feedingTimeItemDtoList)
                .build();
    }

    private List<OrderLineItemDto> mapToOrderLineItemDtoList(List<OrderLineItem> orderLineItemList) {
        return orderLineItemList.stream()
                .map(this::mapToOrderLineItemDto)
                .collect(Collectors.toList());
    }

    private OrderLineItemDto mapToOrderLineItemDto(OrderLineItem orderLineItem) {
        return OrderLineItemDto.builder()
                .id(orderLineItem.getId())
                .animalCode(orderLineItem.getAnimalCode())
                .price(orderLineItem.getPrice())
                .name(orderLineItem.getName())
                .nickname(orderLineItem.getNickname())
                .build();
    }

    private List<FeedingTimeItemDto> mapToFeedingTimeItemDtoList(List<FeedingTimeItem> feedingTimeItemList) {
        return feedingTimeItemList.stream()
                .map(this::mapToFeedingTimeItemDto)
                .collect(Collectors.toList());
    }

    private FeedingTimeItemDto mapToFeedingTimeItemDto(FeedingTimeItem feedingTimeItem) {
        return FeedingTimeItemDto.builder()
                .id(feedingTimeItem.getId())
                .animalCode(feedingTimeItem.getAnimalCode())
                .foodcode(feedingTimeItem.getFoodcode())
                .name(feedingTimeItem.getName())
                .nickname(feedingTimeItem.getNickname())
                .build();
    }

    public boolean deleteOperationItemsByOperationNumber(String operationNumber) {
        List<Operation> itemsToDelete = operationRepository.findByOperationNumber(operationNumber);
        if (!itemsToDelete.isEmpty()) {
            operationRepository.deleteAll(itemsToDelete);
            return true;
        } else {
            return false;
        }
    }

    public Operation updateOperationByOperationNumber(Operation updateOperation, String animalCode) {
        List<Operation> operationOptional = operationRepository.findByOperationNumber(animalCode);
        if (!operationOptional.isEmpty()){
            Operation operation = operationOptional.get(0);
            operation.setDate(updateOperation.getDate());
            operation.setOperationNumber(updateOperation.getOperationNumber());
            operation.setOrderLineItemsList(updateOperation.getOrderLineItemsList());
            operation.setFeedingTimeItemsList(updateOperation.getFeedingTimeItemsList());


            return operationRepository.save(operation);
        }
        return null;
    }


}
