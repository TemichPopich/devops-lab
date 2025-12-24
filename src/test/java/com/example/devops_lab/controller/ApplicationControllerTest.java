package com.example.devops_lab.controller;

import com.example.devops_lab.dto.ApplicationEntityDto;
import com.example.devops_lab.service.ApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.example.devops_lab.ApplicationTestConfiguration.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class ApplicationControllerTest {

    public final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationService applicationService;

    @Test
    void applicationControllerShouldExecuteGetTest() throws Exception {

        // Arrange

        when(applicationService.get(ID)).thenReturn(DTO);

        //Act

        final ResultActions request = mockMvc
                .perform(get("/app/api/v1/get/%s".formatted(ID))
                        .accept(MediaType.APPLICATION_JSON));

        // Assert

        request.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(ENTITY_NAME))
                .andExpect(jsonPath("$.description").value(ENTITY_DESCRIPTION));
    }

    @Test
    void applicationControllerShouldExecutePostTest() throws Exception {

        // Arrange

        when(applicationService.post(any(ApplicationEntityDto.class)))
                .thenReturn(DTO);

        //Act

        final ResultActions request = mockMvc
                .perform(post("/app/api/v1/post")
                        .content(objectMapper.writeValueAsString(DTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        // Assert

        request.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(ENTITY_NAME))
                .andExpect(jsonPath("$.description").value(ENTITY_DESCRIPTION));
    }

    @Test
    void applicationControllerShouldExecutePutTest() throws Exception {

        // Arrange

        when(applicationService.put(eq( ID), any(ApplicationEntityDto.class)))
                .thenReturn(DTO);

        //Act

        final ResultActions request = mockMvc
                .perform(put("/app/api/v1/put/%s".formatted(ID))
                        .content(objectMapper.writeValueAsString(DTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        // Assert

        request.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(ENTITY_NAME))
                .andExpect(jsonPath("$.description").value(ENTITY_DESCRIPTION));
    }

    @Test
    void applicationControllerShouldExecuteDeleteTest() throws Exception {

        // Arrange

        doNothing().when(applicationService).delete(eq(ID));

        //Act

        final ResultActions request = mockMvc
                .perform(delete("/app/api/v1/delete/%s".formatted(ID))
                        .accept(MediaType.APPLICATION_JSON));

        // Assert

        request.andExpect(status().isOk())
                .andExpect(status().isOk());
    }
}
