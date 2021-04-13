package pedrocoelho.javamslearning.msscbrewer.web.mappers;

import org.mapstruct.Mapper;
import pedrocoelho.javamslearning.msscbrewer.domain.Customer;
import pedrocoelho.javamslearning.msscbrewer.web.model.CustomerDto;

@Mapper
public interface CustomerMapper {
    CustomerDto customer2CustomerDto(Customer customer);

    Customer customer2CustomerDto(CustomerDto customerDto);
}
