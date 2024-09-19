package ru.clevertec.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.dto.EngineDto;
import ru.clevertec.entity.EngineEntity;
import ru.clevertec.enums.Fuel;
import ru.clevertec.exception.EngineNotFoundException;
import ru.clevertec.mapper.EngineDtoMapper;
import ru.clevertec.mapper.EngineDtoMapperImpl;
import ru.clevertec.repository.EngineRepository;
import ru.clevertec.util.TestData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

        EngineDto engineDto = TestData.generateEngineDto();
        engineDto.setUuid(uuid);

        //when
        when(engineRepository.getEngineById(uuid))
                .thenReturn(Optional.of(engineEntity));
        when(engineDtoMapper.toEngineDto(engineEntity))
                .thenReturn(engineDto);
        EngineDto engineById = engineService.getEngineById(uuid);

        //then
        assertEquals(Optional.of(engineDto).get().getUuid(), engineById.getUuid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"AWT", "AFN", "CCDA"})
    void shouldGetEngineByFactoryNumber(String factoryNumber) {
        //given

        EngineEntity engine = TestData.generateEngineEntity();
        engine.setFactoryNumber("factoryNumber");


        //when
       when(engineRepository.getEngineByFactoryNumber(factoryNumber))
               .thenReturn(Optional.of(engine));

        //then
        assertDoesNotThrow(
                () -> engineService.getEngineByFactoryNumber(factoryNumber)
        );
    }

    @Test
    void shouldThrowEngineNotFoundExceptionWhenEngineNotFound() {
        //given
        EngineEntity engineEntity = TestData.generateEngineEntity();
        UUID uuid = engineEntity.getUuid();

        EngineDto engineDto = TestData.generateEngineDto();
        engineDto.setUuid(uuid);


        //when
        when(engineRepository.getEngineById(uuid))
                .thenReturn(Optional.empty());
        // then
        assertThrows(
                EngineNotFoundException.class,
                () -> engineService.getEngineById(uuid)
        );
    }

    @Test
    void shouldAddNewEntityInRepository() {
        //given
        EngineDto engineDto = TestData.generateNewEngineDto();
        UUID uuid = engineDto.getUuid();

        EngineEntity engineEntity = TestData.generateNewEngineEntity();
        engineEntity.setUuid(uuid);

        when(engineDtoMapper.toEngineEntity(engineDto))
                .thenReturn(engineEntity);
        when(engineRepository.createEngine(engineEntity))
                .thenReturn(engineEntity);

        //when
        EngineDto engineDtoNew = engineService.createEngine(engineDto);

        //then
        assertThat(engineDtoNew.getUuid()).isEqualTo(engineDtoMapper.toEngineEntity(engineDto).getUuid());

    }

    @Test
    void shouldChangeEngineInRepository() {
        //given
        EngineDto engineDtoNew = TestData.generateNewEngineDto();
        EngineEntity engineEntityNew = TestData.generateNewEngineEntity();

        EngineEntity engineEntityOld = TestData.generateEngineEntity();
        UUID uuid = engineEntityOld.getUuid();

        when(engineDtoMapper.toEngineEntity(engineDtoNew))
                .thenReturn(engineEntityNew);
        //when
        EngineDto engineDto = engineService.updateEngine(uuid, engineDtoNew);

        //then
        assertThat(engineDto.getUuid()).isEqualTo(engineDtoNew.getUuid());

    }

    @Test
    void shouldCallDeleteRepositoryMethodEngine() {
        //given
        UUID uuid = UUID.randomUUID();

        //when
        engineService.deleteEngine(uuid);

        //then
        verify(engineRepository).deleteEngine(uuid);
    }
}