package ru.clevertec.exception;

import java.util.UUID;

public class EngineNotFoundException extends RuntimeException {
    private EngineNotFoundException(String message) {
        super(message);
    }

    public static EngineNotFoundException byEngineId(UUID uuid) {
        return new EngineNotFoundException(
                String.format("Не найден двигатель по uuid = %s", uuid)
                );
    }
    public static EngineNotFoundException byEngineFactoryNumber(String factoryNumber) {
        return new EngineNotFoundException(
                String.format("Не найден двигатель по номеру = %s", factoryNumber)
        );
    }


}
