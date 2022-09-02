package by.reengineering.agency.dto;

import java.util.UUID;

public class ResponseDto {

    private final UUID uuid;
    private final String name;
    private final String model;

    public ResponseDto(UUID uuid, String name, String model) {
        this.uuid = uuid;
        this.name = name;
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public UUID getUuid() {
        return uuid;
    }
}
