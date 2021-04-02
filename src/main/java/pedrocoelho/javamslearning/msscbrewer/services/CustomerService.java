package pedrocoelho.javamslearning.msscbrewer.services;


import pedrocoelho.javamslearning.msscbrewer.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerById(UUID customerId);
}
