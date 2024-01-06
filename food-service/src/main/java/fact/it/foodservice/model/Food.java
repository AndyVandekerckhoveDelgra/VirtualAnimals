package fact.it.foodservice.model;


import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "food")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String foodcode;
    private String name;
}
