package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.dto.EngineDto;
import ru.clevertec.entity.EngineEntity;

import java.util.List;

@Mapper
public interface EngineDtoMapper {
    List<EngineDto> toEngineDtos(List<EngineEntity> engineEntities);
    EngineDto toEngineDto(EngineEntity engineEntity);
    EngineEntity toEngineEntity(EngineDto engineDto);
}
