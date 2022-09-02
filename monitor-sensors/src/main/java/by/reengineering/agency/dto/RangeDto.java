package by.reengineering.agency.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "RangeDto")
@JsonDeserialize(builder = RangeDto.Builder.class)
public class RangeDto {
    private final Double from;
    private final Double to;

    public RangeDto(Double from, Double to) {
        this.from = from;
        this.to = to;
    }

    @Schema(example = "22", description = "Starting distance")
    public Double getFrom() {
        return from;
    }

    @Schema(example = "45", required = true, description = "Ending distance")
    public Double getTo() {
        return to;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {

        private Double from;
        private Double to;

        private Builder() {
        }

        public Builder setFrom(Double from) {
            this.from = from;
            return this;
        }

        public Builder setTo(Double to) {
            this.to = to;
            return this;
        }

        public RangeDto build() {
            return new RangeDto(from, to);
        }
    }
}
