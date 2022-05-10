package com.video.rental.njcode.renting.infraestructure.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.video.rental.njcode.TestSecurityConfig;
import com.video.rental.njcode.renting.application.RentingService;
import com.video.rental.njcode.renting.domain.Renting;
import com.video.rental.njcode.renting.infraestructure.rest.model.RentingPostRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TestSecurityConfig.class)
@AutoConfigureMockMvc
class RentingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentingService service;

    @Test
    void rentingApi_testUnknownApi_shouldReturns404() throws Exception {
        //when then
        mockMvc.perform(post("/renting/new"))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_properlyRentingData_shouldWork() throws Exception {
        //given
        final Renting renting = Renting.createRenting(1l, 1l, Arrays.asList(1l), 1, 1.0, 1.0, new Date());
        given(service.save(any(Renting.class))).willReturn(renting);

        //when then
        mockMvc.perform(post("/renting")
                        .content(asJsonString(createRentingPostRequest(1)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("totalPrice").value("1.0"));
    }

    @Test
    void create_invalidDomainData_shouldFail() throws Exception {
        //given
        final RentingPostRequest rentingPostRequest = createRentingPostRequest(-1);

        //when then
        mockMvc.perform(post("/renting")
                        .content(asJsonString(rentingPostRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void returnFilms_properlyRentingData_shouldWork() throws Exception {
        //given
        final Renting renting = Renting.createRenting(1l, 1l, Arrays.asList(1l), 1, 1.0, 1.0, new Date());
        given(service.returnFilms(anyLong())).willReturn(renting);

        //when then
        mockMvc.perform(put("/renting/returns/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("totalPrice").value("1.0"));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private RentingPostRequest createRentingPostRequest(Integer numberOfDays) {
        RentingPostRequest request = new RentingPostRequest();
        request.setCustomerId(1L);
        request.setFilms(Arrays.asList(1L));
        request.setNumberOfDays(numberOfDays);
        
        return request;
    }
}