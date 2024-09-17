package ru.clevertec.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.clevertec.dto.EngineDto;
import ru.clevertec.enums.Fuel;
import ru.clevertec.service.EngineService;
import ru.clevertec.util.TestData;

import java.util.UUID;

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

    @Test
    void shouldFindEngineById() throws Exception {
        EngineDto engineDto = TestData.generateEngineDto();
        UUID uuid = engineDto.getUuid();
        Mockito.when(this.engineService.getEngineById(uuid))
                .thenReturn(engineDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/%s".formatted(uuid)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(uuid.toString()))
                .andExpect(jsonPath("$.factoryNumber").value("AWT"))
                .andExpect(jsonPath("$.priceEngine").value(1200))
                .andExpect(jsonPath("$.quantity").value(2))
                .andExpect(jsonPath("$.displacement").value(1.8))
                .andExpect(jsonPath("$.fuelType").value(Fuel.GAS.toString()))
                .andExpect(jsonPath("$.powerOfHorse").value(150))
                .andExpect(jsonPath("$.millage").value(300000));
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}