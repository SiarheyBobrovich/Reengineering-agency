package by.reengineering.agency.controllers.api;

import by.reengineering.agency.models.TSingleErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

public interface ViewerAPI {


    @Operation(summary = "Get table of sensors", description = "", tags={ "Views" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TSingleErrorResponse.class)))) })
    @GetMapping(value = "/api/v1/user/sensors/view/all",
            produces = { "application/json" })
    String apiV1UserSensorsViewAllGet(Model model);


    @Operation(summary = "Table of found sensors", description = "", tags={ "Views" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TSingleErrorResponse.class)))) })
    @GetMapping(value = "/api/v1/user/sensors/view")
    String apiV1UserSensorsViewGet(Model model, @Parameter(in = ParameterIn.QUERY, description = "search text" ,required=true,schema=@Schema()) @RequestParam(value = "text", required = true) String text);

}
