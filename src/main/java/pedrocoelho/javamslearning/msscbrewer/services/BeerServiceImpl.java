package pedrocoelho.javamslearning.msscbrewer.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import pedrocoelho.javamslearning.msscbrewer.web.model.BeerDto;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        log.debug("GET request...");
        return BeerDto.builder().id(beerId)
                .name("Amber Leef")
                .beerStyle("Amber")
                .upc(123123123123L)
                .build();
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beer) {
        log.debug("POST request...");
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .name("Super Book")
                .beerStyle("Golden")
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
