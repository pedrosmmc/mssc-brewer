package pedrocoelho.javamslearning.msscbrewer.web.controller.v2;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pedrocoelho.javamslearning.msscbrewer.services.v2.BeerServiceV2;
import pedrocoelho.javamslearning.msscbrewer.web.model.v2.BeerDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Validated
@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {

    private final BeerServiceV2 beerServiceV2;

    public BeerControllerV2(BeerServiceV2 beerServiceV2) {
        this.beerServiceV2 = beerServiceV2;
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> handleGet(@NotNull @PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(beerServiceV2.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@Valid @NotNull @RequestBody BeerDto beer) {
        BeerDto savedBeerDto = beerServiceV2.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        // todo: add hostname to url
        headers.add("Location", "/api/v2/beer/" + savedBeerDto.getId());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{beerId}"})
    public ResponseEntity handleUpdate(@NotNull @PathVariable("beerId") UUID beerId, @Valid @NotNull @RequestBody BeerDto beerDto) {
        beerServiceV2.updateBeer(beerId, beerDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@NotNull @PathVariable UUID beerId) {
        beerServiceV2.deleteBeer(beerId);
    }
}

