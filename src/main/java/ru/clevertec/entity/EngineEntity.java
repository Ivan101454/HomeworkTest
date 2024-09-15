package ru.clevertec.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.clevertec.enums.Fuel;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class EngineEntity {
    private UUID uuid;
    private String FactoryNumber;
    private double priceEngine;
    private int quantity;
    private double displacement ;
    private Fuel fuelType;
    private double powerOfHorse;
    private int millage;
}
