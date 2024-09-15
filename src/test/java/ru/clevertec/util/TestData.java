package ru.clevertec.util;

import ru.clevertec.dto.EngineDto;
import ru.clevertec.entity.EngineEntity;
import ru.clevertec.enums.Fuel;
import ru.clevertec.mapper.EngineDtoMapper;
import ru.clevertec.mapper.EngineDtoMapperImpl;

import java.util.List;
import java.util.UUID;

public class TestData {
    public static EngineEntity generateEngineEntity() {
        return new EngineEntity(UUID.randomUUID(), "AWT", 1200, 2, 1.8, Fuel.GAS, 150, 300000);
    }
    public static EngineDto generateEngineDto() {
        return new EngineDtoMapperImpl().toEngineDto(generateEngineEntity());
    }
    public static List<EngineEntity> generateEngineEntityList() {
        return List.of(
                new EngineEntity(UUID.randomUUID(), "AWT", 1200, 2, 1.8, Fuel.GAS, 150, 300000),
                new EngineEntity(UUID.randomUUID(), "AFN", 700, 10, 1.9, Fuel.DIESEL, 110, 200000),
                new EngineEntity(UUID.randomUUID(), "CCDA", 1500, 1, 2.8, Fuel.GAS, 190, 150000)
        );
    }

}
