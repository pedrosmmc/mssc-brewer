package pedrocoelho.javamslearning.msscbrewer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Null
    private UUID id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;
}
