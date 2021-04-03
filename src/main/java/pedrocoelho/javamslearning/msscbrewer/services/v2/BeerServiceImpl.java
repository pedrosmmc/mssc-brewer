package pedrocoelho.javamslearning.msscbrewer.services.v2;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pedrocoelho.javamslearning.msscbrewer.web.model.v2.BeerDto;
import pedrocoelho.javamslearning.msscbrewer.web.model.v2.BeerStyleEnum;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(beerId)
                .beerName("Amber Leef")
                .beerStyle(BeerStyleEnum.AMBER)
                .build();
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beer) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Super Book")
                .beerStyle(BeerStyleEnum.GOLDEN)
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        //todo: add a real impl to update
        log.debug("Updating beer "+ beerDto.getBeerName() + "(" + beerId + ")" +"...");
    }

    @Override
    public void deleteBeer(UUID beerId) {
        //todo: add real impl to delete
        log.debug("Deleting a beer..." + beerId + ")" +"...");
    }
}
