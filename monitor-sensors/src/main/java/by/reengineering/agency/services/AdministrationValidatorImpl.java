package by.reengineering.agency.services;

import by.reengineering.agency.dao.repository.api.TypeRepository;
import by.reengineering.agency.dto.UpdateSensorDto;
import by.reengineering.agency.entity.Sensor;
import by.reengineering.agency.services.api.AdministrationService;
import by.reengineering.agency.services.api.AdministrationValidator;
import by.reengineering.agency.services.exception.ValidationException;
import by.reengineering.agency.utils.ValidationUtil;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class AdministrationValidatorImpl implements AdministrationValidator {

    private final TypeRepository repository;
    private final AdministrationService<Sensor> administrationService;
    private final ConversionService conversionService;

    public AdministrationValidatorImpl(TypeRepository repository, AdministrationService<Sensor> administrationService, ConversionService conversionService) {
        this.repository = repository;
        this.administrationService = administrationService;
        this.conversionService = conversionService;
    }

    @Override
    public void saveSensor(UpdateSensorDto updateSensorDto) {
        ValidationException validationException = new ValidationException();
        checkCreateSensorDto(updateSensorDto, validationException);

        Sensor newSensor = conversionService.convert(updateSensorDto, Sensor.class);

        administrationService.saveSensor(newSensor);
    }

    @Override
    public void updateSensor(UpdateSensorDto updateSensorDto) {
        final ValidationException validationException = new ValidationException();
        checkUuidSensorDto(updateSensorDto, validationException);

        Sensor updatedSensor = conversionService.convert(updateSensorDto, Sensor.class);

        administrationService.updateSensor(updatedSensor);
    }

    @Override
    public void deleteSensor(UUID id) {
        if (Objects.isNull(id)) {
            ValidationException validationException = new ValidationException();
            validationException.add(Map.entry("uuid", ""));
            throw validationException;
        }

        administrationService.deleteSensor(id);
    }

    private void checkUuidSensorDto(final UpdateSensorDto updateSensorDto, final ValidationException validationException) {
        if (ValidationUtil.isInvalidUuid(updateSensorDto.getUuid())) {
            validationException.add(Map.entry("uuid", "must not be null"));
        }

        checkCreateSensorDto(updateSensorDto, validationException);
    }

    private void checkCreateSensorDto(final UpdateSensorDto updateSensorDto, final ValidationException validationException) {

        if (ValidationUtil.isInvalidName(updateSensorDto.getName())) {
            validationException.add(Map.entry("name", "must not be empty and less than 30 chars"));
        }

        if (ValidationUtil.isInvalidModel(updateSensorDto.getModel())) {
            validationException.add(Map.entry("model", "must not be empty and less than 15 chars"));
        }

        if (ValidationUtil.isInvalidRange(updateSensorDto.getRange())) {
            validationException.add(Map.entry("range", "range 'to' must not be less than 'from' or empty"));
        }

        if (Objects.isNull(updateSensorDto.getType()) || !repository.getAllTypes().contains(updateSensorDto.getType())) {
            validationException.add(Map.entry("type", "must not be null and must be exists"));
        }

        if (!Objects.isNull(updateSensorDto.getUnit()) && !repository.getAllUnits().contains(updateSensorDto.getUnit())) {
            validationException.add(Map.entry("unit", "does not exist"));
        }

        if (ValidationUtil.isInvalidLocation(updateSensorDto.getLocation())) {
            validationException.add(Map.entry("location", "length must be less than 40 chars"));
        }

        if (ValidationUtil.isInvalidDescription(updateSensorDto.getDescription())) {
            validationException.add(Map.entry("description", "length must be less than 200 chars"));
        }

        if (!validationException.isEmpty()) {
            throw validationException;
        }
    }
}
