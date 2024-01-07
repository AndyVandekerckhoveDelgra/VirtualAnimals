package fact.it.operationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "operations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String operationNumber;
    private LocalDateTime date;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItem> orderLineItemsList;
    @OneToMany(cascade = CascadeType.ALL)
    private List<FeedingTimeItem> feedingTimeItemsList;
}
