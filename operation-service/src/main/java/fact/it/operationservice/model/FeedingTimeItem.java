package fact.it.operationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "orderlineitem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedingTimeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private String foodcode;
    private String nickname;
    private String name;

}
