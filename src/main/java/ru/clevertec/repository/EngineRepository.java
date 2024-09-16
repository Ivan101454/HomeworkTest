package ru.clevertec.repository;

import org.springframework.stereotype.Repository;
import ru.clevertec.entity.EngineEntity;
import ru.clevertec.enums.Fuel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EngineRepository {
    private static final List<EngineEntity> db = new ArrayList<>(List.of(
            new EngineEntity(UUID.randomUUID(), "AWT", 1200, 2, 1.8, Fuel.GAS, 150, 300000),
            new EngineEntity(UUID.randomUUID(), "AFN", 700, 10, 1.9, Fuel.DIESEL, 110, 200000),
            new EngineEntity(UUID.randomUUID(), "CCDA", 1500, 1, 2.8, Fuel.GAS, 190, 150000)
    ));

    public List<EngineEntity> getEngines() {
        return db;
    }

    public Optional<EngineEntity> getEngineById(UUID uuid) {
        EngineEntity engine = null;
        int id = 0;
        for (EngineEntity eng : db) {
            if (eng.getUuid().equals(uuid)) {
                engine = eng;
                break;
            }
            id++;
        }
        return Optional.ofNullable(engine);
    }

    public EngineEntity createEngine(EngineEntity engineEntity) {
        db.add(engineEntity);
        return engineEntity;
    }

    public EngineEntity updateEngine(UUID uuid, EngineEntity engineEntity) {
        int id = 0;
        for (EngineEntity eng : db) {
            if (eng.getUuid().equals(uuid)) {
                break;
            }
            id++;
        }
        if (db.size() != id) {
            db.set(id, engineEntity.setUuid(uuid));
        }
        return engineEntity;
    }

    public void deleteEngine(UUID uuid) {
        int id = 0;
        for (EngineEntity eng : db) {
            if (eng.getUuid().equals(uuid)) {
                break;
            }
            id++;
        }
        if (db.size() != id) {
            db.remove(id);
        }
    }
}
