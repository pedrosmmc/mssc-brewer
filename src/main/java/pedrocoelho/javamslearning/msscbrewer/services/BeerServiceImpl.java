package pedrocoelho.javamslearning.msscbrewer.services;

import org.springframework.stereotype.Service;
import pedrocoelho.javamslearning.msscbrewer.web.model.BeerDto;

import java.util.UUID;

@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("Amber Leef")
                .beerStyle("Amber")
                .build();
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beer) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Super Book")
                .beerStyle("Golden")
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        //todo: add a real impl to update
    }
}
