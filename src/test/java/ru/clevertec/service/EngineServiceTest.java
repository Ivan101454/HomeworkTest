package ru.clevertec.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.dto.EngineDto;
import ru.clevertec.entity.EngineEntity;
import ru.clevertec.enums.Fuel;
import ru.clevertec.mapper.EngineDtoMapper;
import ru.clevertec.mapper.EngineDtoMapperImpl;
import ru.clevertec.repository.EngineRepository;
import ru.clevertec.util.TestData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EngineServiceTest {
    @Mock
    private EngineDtoMapper engineDtoMapper;
    @Mock
    private EngineRepository engineRepository;
    @InjectMocks
    private EngineService engineService;


    @Test
    @DisplayName("GetListOfEngineDtos")
    void shouldGetListOfEnginesDto() {

        List<EngineEntity> listEntity = TestData.generateEngineEntityList();
        List<EngineDto> listDto = new ArrayList<>();
        for (EngineEntity eng : listEntity) {
            listDto.add(new EngineDto(eng.getUuid(), eng.getFactoryNumber(), eng.getPriceEngine(),
                    eng.getQuantity(), eng.getDisplacement(), eng.getFuelType(),
                    eng.getPowerOfHorse(), eng.getMillage()));
        }
        //given
        when(engineRepository.getEngines())
                .thenReturn(listEntity);
        when(engineDtoMapper.toEngineDtos(listEntity))
                .thenReturn(listDto);

        //when
        List<EngineDto> engineDtos = engineService.getEngines();
        //then
        assertFalse(engineDtos.isEmpty());
    }

    @Test
    void shouldGetEngineById() {
        //given
        EngineEntity engineEntity = TestData.generateEngineEntity();
        UUID uuid = engineEntity.getUuid();

        List<EngineEntity> listEntity = TestData.generateEngineEntityList();
        listEntity.getFirst().setUuid(uuid);

        EngineDto engineDto = TestData.generateEngineDto();
        engineDto.setUuid(uuid);


        engineDto.setUuid(uuid);
        when(engineRepository.getEngines())
                .thenReturn(listEntity);
        when(engineDtoMapper.toEngineDto(engineEntity))
                .thenReturn(engineDto);
//

        //when
        Optional<EngineDto> engineById = engineService.getEngineById(uuid);

        //then
        assertEquals(Optional.of(engineDto).get().getUuid(), engineById.get().getUuid());
    }

    @Test
    void createEngine() {
    }

    @Test
    void updateEngine() {
    }

    @Test
    void deleteEngine() {
    }
}