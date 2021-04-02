package pedrocoelho.javamslearning.msscbrewer.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pedrocoelho.javamslearning.msscbrewer.services.CustomerService;
import pedrocoelho.javamslearning.msscbrewer.web.model.CustomerDto;

import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {
    private CustomerService customerService;

    public CustomerController (CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping({"/{customerId}"})
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId") UUID customerId) {
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }
}
