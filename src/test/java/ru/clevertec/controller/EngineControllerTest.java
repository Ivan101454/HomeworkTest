package ru.clevertec.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.clevertec.dto.EngineDto;
import ru.clevertec.enums.Fuel;
import ru.clevertec.service.EngineService;
import ru.clevertec.util.TestData;

import java.util.UUID;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EngineController.class)
class EngineControllerTest {
    @MockBean
    EngineService engineService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private static ObjectMapper mapper = new ObjectMapper();


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
        //given
        EngineDto engineDto = TestData.generateEngineDto();
        UUID uuid = engineDto.getUuid();
        when(engineService.getEngineById(uuid))
                .thenReturn(engineDto);
        //when, then
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
    void shouldCreateNewEngineAndReturnDto() throws Exception {
        //given
        EngineDto engineDto = TestData.generateEngineDto();
        UUID uuid = engineDto.getUuid();
        when(engineService.createEngine(engineDto))
                .thenReturn(engineDto);
        //when, then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(engineDto)))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.uuid").value(uuid.toString()));
    }

    @Test
    void shouldUpdateEngine() throws Exception {
        //given
        EngineDto engineDto = TestData.generateEngineDto();
        UUID uuid = engineDto.getUuid();
        when(this.engineService.updateEngine(uuid, engineDto))
                .thenReturn(engineDto);
        //when, then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/update/%s".formatted(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(engineDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(uuid.toString()));
    }

    @Test
    void shouldDeleteEngine() throws Exception {
        //given
        EngineDto engineDto = TestData.generateEngineDto();
        UUID uuid = engineDto.getUuid();

        //when, then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/delete/%s".formatted(uuid)))
                .andExpect(status().isOk());
    }
}