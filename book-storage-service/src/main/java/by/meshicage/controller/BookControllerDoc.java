package by.meshicage.controller;

import by.meshicage.dto.book.*;
import by.meshicage.exception.dto.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public interface BookControllerDoc {

    @Operation(summary = "Create a new book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the book"),
            @ApiResponse(responseCode = "400", description = "Invalid data for creating the book",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreatedBookDto saveBook(
            @Parameter(description = "Data for creating a new book")
            @RequestBody @Valid CreateBookDto createBookDto
    );

    @Operation(summary = "Get a book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the book"),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping("/{id}")
    CreatedBookDto getBook(
            @Parameter(description = "ID of the book to retrieve", example = "1")
            @PathVariable Long id
    );

    @Operation(summary = "Get all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all books")
    })
    @GetMapping("/all")
    Page<CreatedBookDto> getAllBooks(Integer pageNumber, Integer pageSize);

    @Operation(summary = "Get a book by ISBN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the book"),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping
    CreatedBookDto getBook(
            @Parameter(description = "ISBN of the book to retrieve", example = "978-3-12-164410-0")
            @RequestParam("isbn") String ISBN
    );

    @Operation(summary = "Fully update a book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the book"),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data for updating the book",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PutMapping("/{id}")
    UpdatedBookDto fullBookUpdate(
            @Parameter(description = "ID of the book to update", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Data for full update of the book")
            @RequestBody @Valid FullBookUpdateDto bookUpdateDto
    );

    @Operation(summary = "Partially update a book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully partially updated the book"),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data for partial update of the book",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PatchMapping("/{id}")
    UpdatedBookDto partBookUpdate(
            @Parameter(description = "ID of the book to partially update", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Data for partial update of the book")
            @RequestBody PartUpdateBookDto partUpdateBookDto
    );

    @Operation(summary = "Delete a book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the book"),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteBook(@PathVariable Long id);
}
