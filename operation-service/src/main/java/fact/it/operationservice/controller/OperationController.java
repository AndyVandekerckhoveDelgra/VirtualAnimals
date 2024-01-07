package fact.it.operationservice.controller;

import fact.it.operationservice.dto.OperationRequest;
import fact.it.operationservice.dto.OperationResponse;
import fact.it.operationservice.model.Operation;
import fact.it.operationservice.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/operation")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String placeOrder(@RequestBody OperationRequest operationRequest) {
        boolean result = operationService.placeOrder(operationRequest);
        return (result ? "Order placed successfully" : "Order placement failed");
    }

    @PostMapping("/feeding")
    @ResponseStatus(HttpStatus.OK)
    public String placeFeedingTime(@RequestBody OperationRequest operationRequest) {
        boolean result = operationService.placeFeedingTime(operationRequest);
        return (result ? "Feeding done successfully" : "Feeding failed");
    }
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<OperationResponse> getAllProducts() {
        return operationService.getAllOrders();
    }

    @GetMapping("/after/{date}")
    public List<OperationResponse> getOperationsAfterDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return operationService.getOperationsAfterDate(date);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OperationResponse> getOperationByOperationNumber
            (@RequestParam List<String> operationNumber) {
        return operationService.getAllOperationByOperationNumber(operationNumber);
    }
    @DeleteMapping("/delete/{operationNumber}")
    public ResponseEntity<String> deleteOperationItemsByOperationNumber(@PathVariable String operationNumber) {
        boolean deleted = operationService.deleteOperationItemsByOperationNumber(operationNumber);
        if (deleted) {
            return ResponseEntity.ok("Operation items deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Operation items not found");
        }
    }


    @PutMapping("/update/{operationNumber}")
    public ResponseEntity<Operation> updateOperation(@RequestBody Operation updateOperation, @PathVariable("operationNumber") String operationNumber){
        Operation operation = operationService.updateOperationByOperationNumber(updateOperation, operationNumber);
        if (operation==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(operation, HttpStatus.OK);
    }

}