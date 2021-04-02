package pedrocoelho.javamslearning.msscbrewer.services;

import pedrocoelho.javamslearning.msscbrewer.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);
}