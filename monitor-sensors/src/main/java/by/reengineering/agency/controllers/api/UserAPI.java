package by.reengineering.agency.controllers.api;

import by.reengineering.agency.models.TSingleErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserAPI {

    @Operation(summary = "Login form", description = "", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")})
    @GetMapping(value = "/api/v1/login")
    String apiV1LoginGet();


    @Operation(summary = "Login service for users", description = "", tags={ "Users" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sucsessful", content = @Content(mediaType = "text/html", schema = @Schema(implementation = String.class))),

            @ApiResponse(responseCode = "401", description = "To make a request to this address, you need to pass an authorization token"),

            @ApiResponse(responseCode = "403", description = "This authorization token is prohibited from making requests to this address"),

            @ApiResponse(responseCode = "404", description = "invalid username or password", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TSingleErrorResponse.class)))),

            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TSingleErrorResponse.class)))) })
    @PostMapping(value = "/api/v1/login",
            produces = { "text/html", "application/json" })
    ResponseEntity<String> apiV1LoginPost(@Parameter(in = ParameterIn.QUERY, description = "login" ,required=true,schema=@Schema()) @RequestParam(value = "username", required = true) String username, @Parameter(in = ParameterIn.QUERY, description = "login" ,required=true,schema=@Schema()) @RequestParam(value = "password") String password);

}
