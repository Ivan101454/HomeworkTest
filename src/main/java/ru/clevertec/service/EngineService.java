package ru.clevertec.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.EngineDto;
import ru.clevertec.entity.EngineEntity;
import ru.clevertec.exception.EngineNotFoundException;
import ru.clevertec.mapper.EngineDtoMapper;
import ru.clevertec.mapper.EngineDtoMapperImpl;
import ru.clevertec.repository.EngineRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EngineService {
    private final EngineRepository engineRepository;
    private final EngineDtoMapper engineDtoMapper;

    public List<EngineDto> getEngines() {
        List<EngineEntity> engines = engineRepository.getEngines();
        return engineDtoMapper.toEngineDtos(engines);
    }
    public EngineDto getEngineById(UUID uuid) {
        EngineEntity engineEntity = engineRepository.getEngineById(uuid).orElseThrow(() -> EngineNotFoundException.byEngineId(uuid));
        return engineDtoMapper.toEngineDto(engineEntity);
    }
    public EngineDto createEngine(EngineDto engineDto) {
        engineRepository.createEngine(engineDtoMapper.toEngineEntity(engineDto));
        return engineDto;
    }
    public EngineDto updateEngine(UUID uuid, EngineDto engineDto) {
        engineRepository.updateEngine(uuid, engineDtoMapper.toEngineEntity(engineDto));
        return engineDto;
    }
    public void deleteEngine(UUID uuid) {
        engineRepository.deleteEngine(uuid);
    }
}
