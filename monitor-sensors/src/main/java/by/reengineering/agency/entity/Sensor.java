package by.reengineering.agency.entity;

import java.util.UUID;

public class Sensor {
    private UUID uuid;
    private String name;
    private String model;
    private Range range;
    private String type;
    private String unit;
    private String location;
    private String description;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Builder() {
        }

        private UUID uuid;
        private String name;
        private String model;
        private String unit;
        private Range range;
        private String type;
        private String location;
        private String description;

        public Builder uuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder unit(String unit) {
            this.unit = unit;
            return this;
        }

        public Builder range(Range range) {
            this.range = range;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Sensor build() {
            Sensor sensor = new Sensor();

            sensor.setUuid(uuid);
            sensor.setName(name);
            sensor.setModel(model);
            sensor.setUnit(unit);
            sensor.setRange(range);
            sensor.setType(type);
            sensor.setLocation(location);
            sensor.setDescription(description);

            return sensor;
        }
    }
}
