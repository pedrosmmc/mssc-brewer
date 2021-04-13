package pedrocoelho.javamslearning.msscbrewer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pedrocoelho.javamslearning.msscbrewer.web.model.v2.BeerStyleEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer {
    @Null
    private UUID id;

    @NotBlank
    private String name;

    //todo: enum validation
    private BeerStyleEnum style;

    @Positive
    private Long upc;

    private Timestamp createdDate;

    private Timestamp updatedDate;
}
