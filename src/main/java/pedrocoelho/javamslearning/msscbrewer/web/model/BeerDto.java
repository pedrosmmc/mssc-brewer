package pedrocoelho.javamslearning.msscbrewer.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data //Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check all non-transient fields.
@NoArgsConstructor //Generates a no-args constructor.
@AllArgsConstructor // Generates an all-args constructor.
@Builder //The builder annotation creates a so-called 'builder' aspect to the class that is annotated or the class that contains a member which is annotated with @Builder.
public class BeerDto {
    private UUID id;
    private String beerName;
    private String beerStyle;
    private Long upc;
}
