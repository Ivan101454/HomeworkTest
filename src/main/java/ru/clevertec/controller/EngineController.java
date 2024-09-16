package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.EngineDto;
import ru.clevertec.service.EngineService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EngineController {

    private final EngineService engineService;
    @GetMapping("api/v1/engines")
    public ResponseEntity<List<EngineDto>> findAll() {
        List<EngineDto> engines = engineService.getEngines();
        return ResponseEntity.ok(engines);
    }
}
