package by.reengineering.agency.controllers.api;

import by.reengineering.agency.dto.CreateSensorDto;
import by.reengineering.agency.dto.UpdateSensorDto;
import by.reengineering.agency.models.TMultipleErrorResponse;
import by.reengineering.agency.models.TSingleErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface AdministrationAPI {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sensor deleted successful"),

            @ApiResponse(responseCode = "401", description = "To make a request to this address, you need to pass an authorization token"),

            @ApiResponse(responseCode = "403", description = "This authorization token is prohibited from making requests to this address"),

            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TSingleErrorResponse.class)))) })
    @DeleteMapping(value = "/api/v1/admin/sensors/delete/{uuid}",
            produces = { "application/json" })
    void apiV1AdminSensorsDeleteUuidDelete(@Parameter(in = ParameterIn.PATH, description = "Identificator of sensor", required=true, schema=@Schema()) @PathVariable("uuid") UUID uuid);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sensor created successful"),

            @ApiResponse(responseCode = "400", description = "The server cannot or will not process the request due to an apparent client error", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TMultipleErrorResponse.class)))),

            @ApiResponse(responseCode = "401", description = "To make a request to this address, you need to pass an authorization token"),

            @ApiResponse(responseCode = "403", description = "This authorization token is prohibited from making requests to this address"),

            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = TSingleErrorResponse.class)))) })
    @PostMapping(value = "/api/v1/admin/sensors",
            produces = { "application/json" },
            consumes = { "application/json" })
    void apiV1AdminSensorsPost(@Parameter(in = ParameterIn.DEFAULT, description = "Sensor body", required=true, schema=@Schema(implementation = CreateSensorDto.class)) @RequestBody CreateSensorDto body);


    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sensor undated successful"),

            @ApiResponse(responseCode = "400", description = "The server cannot or will not process the request due to an apparent client error", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TMultipleErrorResponse.class)))),

            @ApiResponse(responseCode = "401", description = "To make a request to this address, you need to pass an authorization token"),

            @ApiResponse(responseCode = "403", description = "This authorization token is prohibited from making requests to this address"),

            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TSingleErrorResponse.class)))) })
    @PutMapping(value = "/api/v1/admin/sensors",
            produces = { "application/json" },
            consumes = { "application/json" })
    void apiV1AdminSensorsPut(@Parameter(in = ParameterIn.DEFAULT, description = "Sensor body", required=true, schema=@Schema()) @RequestBody UpdateSensorDto body);

}
