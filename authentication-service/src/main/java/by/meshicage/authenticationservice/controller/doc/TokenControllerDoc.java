package by.meshicage.authenticationservice.controller.doc;

import by.meshicage.authenticationservice.dto.UserRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CREATED;

public interface TokenControllerDoc {

    @Operation(summary = "Create a token for user authentication",
            description = "This endpoint allows you to create a token for authenticating a user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Token successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid data for authentication")
    })
    @GetMapping
    @ResponseStatus(CREATED)
    String createToken(
            @Parameter(description = "Data for user authentication", required = true)
            @RequestBody @Valid UserRequestDto authenticationData
    );
}