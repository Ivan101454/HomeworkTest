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
        return new EngineDto(UUID.randomUUID(), "AWT", 1200, 2, 1.8, Fuel.GAS, 150, 300000);
    }
    public static EngineEntity generateNewEngineEntity() {
        return new EngineEntity(UUID.randomUUID(), "CAXA", 900, 1, 1.4, Fuel.GAS, 122, 100000);
    }
    public static EngineDto generateNewEngineDto() {
        return new EngineDto(UUID.randomUUID(), "CAXA", 900, 1, 1.4, Fuel.GAS, 122, 100000);
    }
    public static List<EngineEntity> generateEngineEntityList() {
        return List.of(
                new EngineEntity(UUID.randomUUID(), "AWT", 1200, 2, 1.8, Fuel.GAS, 150, 300000),
                new EngineEntity(UUID.randomUUID(), "AFN", 700, 10, 1.9, Fuel.DIESEL, 110, 200000),
                new EngineEntity(UUID.randomUUID(), "CCDA", 1500, 1, 2.8, Fuel.GAS, 190, 150000)
        );
    }

}
