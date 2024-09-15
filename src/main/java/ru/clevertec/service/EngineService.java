package ru.clevertec.service;

import ru.clevertec.dto.EngineDto;
import ru.clevertec.entity.EngineEntity;
import ru.clevertec.repository.EngineRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EngineService {
    private final EngineRepository engineRepository = new EngineRepository();

    public List<EngineDto> getEngines() {
        return engineRepository.getEngines();
    }
    public Optional<EngineEntity> getEngineById(UUID uuid) {
        EngineEntity engine = null;
        for (EngineEntity eng : db) {
            if (eng.getUuid().equals(uuid)) {
                engine = eng;
            }
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
        db.set(id, engineEntity);
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
        db.remove(id);
    }
}
