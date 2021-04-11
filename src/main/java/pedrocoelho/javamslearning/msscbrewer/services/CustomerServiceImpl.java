package pedrocoelho.javamslearning.msscbrewer.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pedrocoelho.javamslearning.msscbrewer.web.model.CustomerDto;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder()
                .id(customerId)
                .name("Peter Pan")
                .build();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Peter Pan")
                .build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        log.debug("Updated customer " + customerId + "...");
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        log.debug("Deleted customer " + customerId + "...");
    }
}
