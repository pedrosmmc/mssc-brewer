package pedrocoelho.javamslearning.msscbrewer.web.mappers;

import org.mapstruct.Mapper;
import pedrocoelho.javamslearning.msscbrewer.domain.Beer;
import pedrocoelho.javamslearning.msscbrewer.web.model.v2.BeerDto;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    BeerDto beer2BeerDto(Beer beer);

    Beer beerDto2Beer(BeerDto beerDto);
}
