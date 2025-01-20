package by.meshicage.controller.doc;

import by.meshicage.applicationexceptionstarter.dto.ExceptionResponse;
import by.meshicage.dto.user.UserRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
            @ApiResponse(responseCode = "400", description = "Invalid data for authentication",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping
    @ResponseStatus(CREATED)
    String createToken(
            @Parameter(description = "Data for user authentication", required = true)
            @RequestBody @Valid UserRequestDto authenticationData
    );
}