package ru.clevertec.service;

import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import ru.clevertec.dto.EngineDto;
import ru.clevertec.entity.EngineEntity;
import ru.clevertec.mapper.EngineDtoMapper;
import ru.clevertec.mapper.EngineDtoMapperImpl;
import ru.clevertec.repository.EngineRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class EngineService {
    private EngineRepository engineRepository;
    private EngineDtoMapper engineDtoMapper;

    public List<EngineDto> getEngines() {
        List<EngineEntity> engines = engineRepository.getEngines();
        return engineDtoMapper.toEngineDtos(engines);
    }
    public Optional<EngineDto> getEngineById(UUID uuid) {
        EngineEntity engine = null;
        for (EngineEntity eng : engineRepository.getEngines()) {
            if (eng.getUuid().equals(uuid)) {
                engine = eng;
            }
        }
        return Optional.ofNullable(engineDtoMapper.toEngineDto(engine));
    }
    public EngineDto createEngine(EngineDto engineDto) {
        engineRepository.getEngines().add(engineDtoMapper.toEngineEntity(engineDto));
        return engineDto;
    }
    public EngineDto updateEngine(UUID uuid, EngineDto engineDto) {
        int id = 0;
        for (EngineEntity eng : engineRepository.getEngines()) {
            if (eng.getUuid().equals(uuid)) {
                break;
            }
            id++;
        }
        if (engineRepository.getEngines().size() != id) {
            engineRepository.getEngines().set(id, engineDtoMapper.toEngineEntity(engineDto));
        }
        return engineDto;
    }
    public void deleteEngine(UUID uuid) {
        int id = 0;
        for (EngineEntity eng : engineRepository.getEngines()) {
            if (eng.getUuid().equals(uuid)) {
                break;
            }
            id++;
        }
        if (engineRepository.getEngines().size() != id) {
            engineRepository.getEngines().remove(id);
        }
    }
}
