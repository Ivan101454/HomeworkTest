package ru.clevertec.repository;

import org.junit.jupiter.api.Test;
import ru.clevertec.entity.EngineEntity;
import ru.clevertec.enums.Fuel;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EngineRepositoryTest {


    @Test
    void getEngines() {
    }

    @Test
    void getEngineById() {
        EngineRepository engineRepository = new EngineRepository();
        EngineEntity engine = engineRepository.getEngines().get(2);
        Optional<EngineEntity> engineById = engineRepository.getEngineById(engine.getUuid());
        System.out.println();
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