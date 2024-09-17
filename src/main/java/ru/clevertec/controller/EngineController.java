package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.EngineDto;
import ru.clevertec.service.EngineService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class EngineController {

    private final EngineService engineService;

    @GetMapping("api/v1/engines")
    public ResponseEntity<List<EngineDto>> findAll() {
        List<EngineDto> engines = engineService.getEngines();
        return ResponseEntity.ok(engines);
    }
    @GetMapping("api/v1/{id}")
    public ResponseEntity<EngineDto> findById(@PathVariable("id") String id) {
        UUID uuid = UUID.fromString(id);
        EngineDto engineById = engineService.getEngineById(uuid);
        return ResponseEntity.ok(engineById);
    }
    @PostMapping("api/v1/create")
    public ResponseEntity<EngineDto> create(EngineDto engine) {
        EngineDto engineDto = engineService.createEngine(engine);
        return ResponseEntity.ok(engineDto);
    }
    @PostMapping("api/v1/update/{id}")
    public ResponseEntity<EngineDto> update(@PathVariable("id") String id, EngineDto engine) {
        UUID uuid = UUID.fromString(id);
        EngineDto engineDto = engineService.updateEngine(uuid, engine);
        return ResponseEntity.ok(engineDto);
    }
    @PostMapping("api/v1/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        UUID uuid = UUID.fromString(id);
        engineService.deleteEngine(uuid);
    }
}
