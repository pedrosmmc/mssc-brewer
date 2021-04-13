package pedrocoelho.javamslearning.msscbrewer.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pedrocoelho.javamslearning.msscbrewer.services.BeerService;
import pedrocoelho.javamslearning.msscbrewer.web.model.BeerDto;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {

    @MockBean
    BeerService beerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    BeerDto validBeer;

    @BeforeEach
    public void setUp() {
        validBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .name("Sagres")
                .style("Xixi")
                .upc(123456789012L)
                .build();
    }

    @Test
    public void handleGet() throws Exception {
        given(beerService.getBeerById(any(UUID.class))).willReturn(validBeer);

        mockMvc.perform(get("/api/v1/beer/" + validBeer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validBeer.getId().toString())))
                .andExpect(jsonPath("$.name", is(validBeer.getName())));
    }

    @Test
    public void handlePost() throws Exception {
        BeerDto savedBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .name("Tuborg")
                .style("Golden")
                .upc(123435256564L)
                .build();

        String beerDtoJson = objectMapper.writeValueAsString(savedBeer);

        given(beerService.saveNewBeer(any())).willReturn(savedBeer);

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void handlePut() throws Exception {
        BeerDto beerDto = validBeer;
        beerDto.setId(null);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());

        then(beerService).should().updateBeer(any(), any());
    }

    @Test
    public void handleDelete() throws Exception {
        BeerDto beerDto = validBeer;
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(delete("/api/v1/beer/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(beerDtoJson))
                .andExpect(status().isNoContent());

        then(beerService).should().deleteBeer(any());
    }
}