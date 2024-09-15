package ru.clevertec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.enums.Fuel;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EngineDto {
    private UUID uuid;
    private String FactoryNumber;
    private double priceEngine;
    private int quantity;
    private double displacement ;
    private Fuel fuelType;
    private double powerOfHorse;
    private int millage;
}
