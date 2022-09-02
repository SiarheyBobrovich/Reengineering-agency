package by.reengineering.agency.controllers;

import by.reengineering.agency.controllers.api.AdministrationAPI;
import by.reengineering.agency.dto.CreateSensorDto;
import by.reengineering.agency.dto.UpdateSensorDto;
import by.reengineering.agency.services.api.AdministrationValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Tag(name = "Sensors")
public class AdministrationController implements AdministrationAPI {

    private final AdministrationValidator validator;

    public AdministrationController(AdministrationValidator validator) {
        this.validator = validator;
    }

    @Operation(summary = "Add a new sensor", description = "", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Sensors" })
    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    public void apiV1AdminSensorsPost(CreateSensorDto createSensorDto) {
        UpdateSensorDto updateSensorDto = UpdateSensorDto.builder()
                .setName(createSensorDto.getName())
                .setModel(createSensorDto.getModel())
                .setRange(createSensorDto.getRange())
                .setType(createSensorDto.getType())
                .setUnit(createSensorDto.getUnit())
                .setLocation(createSensorDto.getLocation())
                .setDescription(createSensorDto.getDescription())
                .build();

        validator.saveSensor(updateSensorDto);
    }
    @Operation(summary = "Update a saved sensor", description = "", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Sensors" })
    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    public void apiV1AdminSensorsPut(UpdateSensorDto body) {
        validator.updateSensor(body);
    }

    @Operation(summary = "Delete saved sensor", description = "", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Sensors" })
    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void apiV1AdminSensorsDeleteUuidDelete(UUID uuid) {
        validator.deleteSensor(uuid);
    }
}
