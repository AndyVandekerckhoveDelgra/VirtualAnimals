package fact.it.adoptedanimalservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdoptedAnimalResponse {
    private String animalCode;
    private String nickname;
}