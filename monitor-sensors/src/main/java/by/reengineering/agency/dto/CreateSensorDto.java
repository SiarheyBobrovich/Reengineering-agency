package by.reengineering.agency.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "CreateSensorDto")
@JsonDeserialize(builder = CreateSensorDto.Builder.class)
public class CreateSensorDto {

    private final String name;
    private final String model;
    private final RangeDto range;
    private final String type;
    private final String unit;
    private final String location;
    private final String description;

    public CreateSensorDto( String name, String model, RangeDto range, String type, String unit, String location, String description) {
        this.name = name;
        this.model = model;
        this.range = range;
        this.type = type;
        this.unit = unit;
        this.location = location;
        this.description = description;
    }


    @Schema(example = "Barometer", required = true, description = "title")
    public String getName() {
        return name;
    }

    @Schema(example = "ac-23", required = true, description = "title")
    public String getModel() {
        return model;
    }

    @Schema(description = "")
    public RangeDto getRange() {
        return range;
    }

    @Schema(example = "Pressure", required = true, description = "Sensor types:   * `Pressure`   * `Voltage`   * `Temperature`   * `Humidity` ")
    public String getType() {
        return type;
    }

    @Schema(example = "bars", description = "Sensor units:   * `bars`   * `voltage`   * `°С`   * `%` ")
    public String getUnit() {
        return unit;
    }

    @Schema(example = "kitchen", description = "sensor location")
    public String getLocation() {
        return location;
    }

    @Schema(example = "description", description = "sensor description")
    public String getDescription() {
        return description;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private String name;
        private String model;
        private RangeDto range;
        private String type;
        private String unit;
        private String location;
        private String description;

        private Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setRange(RangeDto range) {
            this.range = range;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setUnit(String unit) {
            this.unit = unit;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public CreateSensorDto build() {
            return new CreateSensorDto(name, model, range, type, unit, location, description);
        }
    }
}
