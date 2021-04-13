package pedrocoelho.javamslearning.msscbrewer.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pedrocoelho.javamslearning.msscbrewer.services.CustomerService;
import pedrocoelho.javamslearning.msscbrewer.web.model.CustomerDto;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    CustomerDto validCustomer;

    @BeforeEach
    public void setUp() {
        validCustomer = CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Leonardo Da Vinci")
                .build();
    }

    @Test
    public void handleGet() throws Exception {
        given(customerService.getCustomerById(any(UUID.class))).willReturn(validCustomer);

        mockMvc.perform(get("/api/v1/customer/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validCustomer.getId().toString())))
                .andExpect(jsonPath("$.name", is(validCustomer.getName())));
    }

    @Test
    public void handlePost() throws Exception {
        CustomerDto customerDto = validCustomer;
        customerDto.setId(null);
        CustomerDto savedCustomer = CustomerDto.builder()
                .id(UUID.randomUUID()).name("Nietzsche").build();
        String customerDtoJson = objectMapper.writeValueAsString(customerDto);

        given(customerService.createCustomer(any())).willReturn(savedCustomer);

        mockMvc.perform(post("/api/v1/customer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void handlePut() throws Exception {
        CustomerDto customerDto = validCustomer;
        customerDto.setId(null);
        String customerDtoJson = objectMapper.writeValueAsString(customerDto);

        mockMvc.perform(put("/api/v1/customer/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerDtoJson))
                .andExpect(status().isNoContent());

        then(customerService).should().updateCustomer(any(), any());
    }

    @Test
    public void handleDelete() throws Exception {
        CustomerDto beerDto = validCustomer;
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(delete("/api/v1/customer/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(beerDtoJson))
                .andExpect(status().isNoContent());

        then(customerService).should().deleteCustomer(any());
    }
}