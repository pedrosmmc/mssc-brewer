package pedrocoelho.javamslearning.msscbrewer.services.v2;

import pedrocoelho.javamslearning.msscbrewer.web.model.v2.BeerDto;

import java.util.UUID;

public interface BeerServiceV2 {
    BeerDto getBeerById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beer);

    void updateBeer(UUID beerId, BeerDto beerDto);

    void deleteBeer(UUID beerId);
}
