package com.example.intra.controller;

import com.example.intra.model.Guess;
import com.example.intra.service.GuessService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(GuessController.class)
public class GuessControllerTests {
    private final MockMvc mockMvc;
    private final ObjectMapper mapper;

    @MockBean
    private GuessService service;

    @Autowired
    GuessControllerTests(MockMvc mockMvc, ObjectMapper mapper) {
        this.mockMvc = mockMvc;
        this.mapper = mapper;
    }

    @Test
    void testAddGuess() throws Exception {
        //Arrange
        Guess expected = new Guess();
        expected.setId(1);
        when(service.addGuess(any())).thenReturn(Optional.of(expected));

        //Act
        MvcResult result = mockMvc.perform(post("/guess")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new Guess()))).andReturn();

        //Assert
        var actual = mapper.readValue(result.getResponse().getContentAsString(), Guess.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actual.getId()).isGreaterThan(0);
    }

    @Test
    void testAddGuessFail() throws Exception {
        //Arrange
        when(service.addGuess(any())).thenReturn(Optional.empty());

        //Act
        MvcResult result = mockMvc.perform(post("/guess")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new Guess()))).andReturn();

        //Assert
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
    }

    @Test
    void testGetGuesses() throws Exception {
        //Arrange
        List<Guess> expected = List.of(new Guess(), new Guess(), new Guess());
        when(service.getGuesses()).thenReturn(expected);

        //Act
        MvcResult result = mockMvc.perform(get("/guess")).andReturn();

        //Assert
        var actual = mapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
