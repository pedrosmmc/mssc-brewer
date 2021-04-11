package pedrocoelho.javamslearning.msscbrewer.web.controller.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pedrocoelho.javamslearning.msscbrewer.services.v2.BeerServiceV2;
import pedrocoelho.javamslearning.msscbrewer.web.model.v2.BeerDto;
import pedrocoelho.javamslearning.msscbrewer.web.model.v2.BeerStyleEnum;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerControllerV2.class)
public class BeerControllerV2Test {

    @MockBean
    BeerServiceV2 beerServiceV2;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    UUID id;
    BeerDto validBeer;

    @BeforeEach
    public void setUp() {
        id = UUID.randomUUID();

        validBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .name("Super Bock")
                .style(BeerStyleEnum.STOUT)
                .upc(12345L)
                .build();
    }

    @Test
    public void handleGet() throws Exception {
        validBeer.setId(id);
        given(beerServiceV2.getBeerById(any(UUID.class))).willReturn(validBeer);

        mockMvc.perform(get("/api/v2/beer/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validBeer.getId().toString())))
                .andExpect(jsonPath("$.name", is(validBeer.getName())));
    }

    @Test
    public void handlePost() throws Exception {
        BeerDto savedBeer = validBeer;
        savedBeer.setId(null);
        String beerDtoJson = objectMapper.writeValueAsString(savedBeer);

        given(beerServiceV2.saveNewBeer(any())).willReturn(savedBeer);

        mockMvc.perform(post("/api/v2/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void handlePut() throws Exception {
        //given
        BeerDto beerDto = validBeer;
        beerDto.setId(null);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v2/beer/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());

        then(beerServiceV2).should().updateBeer(any(), any());
    }

    @Test
    public void handleDelete() throws Exception {
        BeerDto beerDto = validBeer;
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(delete("/api/v2/beer/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(beerDtoJson))
                .andExpect(status().isNoContent());

        then(beerServiceV2).should().deleteBeer(any());
    }
}