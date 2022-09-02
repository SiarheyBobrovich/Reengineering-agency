package by.reengineering.agency.converters;

import by.reengineering.agency.dto.RangeDto;
import by.reengineering.agency.dto.UpdateSensorDto;
import by.reengineering.agency.entity.Range;
import by.reengineering.agency.entity.Sensor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SensorDtoToSensorConverter implements Converter<UpdateSensorDto, Sensor> {
    @Override
    public Sensor convert(final UpdateSensorDto source) {
        final RangeDto range = source.getRange();
        Double from = range.getFrom();

        return Sensor.builder()
                .uuid(source.getUuid())
                .name(source.getName())
                .model(source.getModel())
                .unit(source.getUnit())
                .range(new Range(Objects.isNull(from) ? 0.0 : from, range.getTo()))
                .type(source.getType())
                .location(source.getLocation())
                .description(source.getDescription())
                .build();
    }
}
