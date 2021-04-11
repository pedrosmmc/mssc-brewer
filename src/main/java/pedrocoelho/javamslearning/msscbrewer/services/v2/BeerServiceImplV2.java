package pedrocoelho.javamslearning.msscbrewer.services.v2;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pedrocoelho.javamslearning.msscbrewer.web.model.v2.BeerDto;
import pedrocoelho.javamslearning.msscbrewer.web.model.v2.BeerStyleEnum;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImplV2 implements BeerServiceV2 {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(beerId)
                .name("Amber Leef")
                .style(BeerStyleEnum.AMBER)
                .upc(999999999L)
                .build();
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beer) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .name("Super Book")
                .style(BeerStyleEnum.GOLDEN)
                .upc(999999999L)
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        //todo: add a real impl to update
        log.debug("Updating beer "+ beerDto.getName() + "(" + beerId + ")" +"...");
    }

    @Override
    public void deleteBeer(UUID beerId) {
        //todo: add real impl to delete
        log.debug("Deleting a beer..." + beerId + ")" +"...");
    }
}
