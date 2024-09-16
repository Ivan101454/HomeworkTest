package ru.clevertec.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.clevertec.service.EngineService;
import ru.clevertec.util.TestData;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EngineController.class)
class EngineControllerTest {
    @MockBean
    EngineService engineService;
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldFindAll() throws Exception {
        //given
        when(engineService.getEngines())
                .thenReturn(TestData.generateEngineDtoList());

        //when, then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/engines"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));

    }
}