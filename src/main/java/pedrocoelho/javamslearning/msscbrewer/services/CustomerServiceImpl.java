package pedrocoelho.javamslearning.msscbrewer.services;

import org.springframework.stereotype.Service;
import pedrocoelho.javamslearning.msscbrewer.web.model.CustomerDto;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Peter Pan")
                .build();
    }
}
