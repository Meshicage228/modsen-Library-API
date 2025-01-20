package by.meshicage.authenticationservice.controller.doc;

import by.meshicage.applicationexceptionstarter.dto.ExceptionResponse;
import by.meshicage.authenticationservice.dto.CreateUserDto;
import by.meshicage.authenticationservice.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

public interface UserControllerDoc {

    @Operation(summary = "Create a new user",
            description = "This endpoint allows you to create a new user in the system. " +
                    "You must provide necessary details such as username, password, and other required fields.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User  successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid data for creating user",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping
    @ResponseStatus(CREATED)
    UserResponseDto createUser (
            @Parameter(description = "Data for creating a new user", required = true)
            @RequestBody @Valid CreateUserDto createUserDto
    );

    @Operation(summary = "Get user by username and password",
            description = "This endpoint allows you to retrieve a user using their username and password. " +
                    "If the user is not found, a 404 status code will be returned.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User  successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "User  not found",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data for retrieving user",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping("/login")
    UserResponseDto getUser (
            @Parameter(description = "Username of the user", required = true, example = "user123")
            @RequestParam(value = "username") @NotBlank(message = "Provide login") String username,

            @Parameter(description = "Password of the user", required = true, example = "password123")
            @RequestParam(value = "password") @NotBlank(message = "Provide password") String password
    );
}
