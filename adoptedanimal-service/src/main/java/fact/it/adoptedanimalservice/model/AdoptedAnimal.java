package fact.it.adoptedanimalservice.model;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "adoptedanimal")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdoptedAnimal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private String nickname;
}
