package by.meshicage.controller.doc;

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

    @Operation(summary = "Create a new book",
            description = "This endpoint allows you to create a new book in the system. " +
                    "You must provide the necessary details such as ISBN, title, author, description and genre.")
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

    @Operation(summary = "Get a book by ID",
            description = "Retrieve a book's details using its unique ID.")
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

    @Operation(summary = "Get all books",
            description = "Retrieve a paginated list of all books in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all books")
    })
    @GetMapping("/all")
    Page<CreatedBookDto> getAllBooks(
            @Parameter(description = "Page number to retrieve", example = "0")
            @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
            @Parameter(description = "Number of books per page", example = "100")
            @RequestParam(defaultValue = "100", required = false) Integer pageSize
    );

    @Operation(summary = "Get a book by ISBN",
            description = "Retrieve a book's details using its ISBN.")
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

    @Operation(summary = "Fully update a book by ID",
            description = "Update all details of an existing book using its ID.")
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

    @Operation(summary = "Partially update a book by ID",
            description = "Update specific details of an existing book using its ID.")
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

    @Operation(summary = "Delete a book by ID",
            description = "Remove a book from the system using its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the book"),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteBook(
            @Parameter(description = "ID of the book to delete", example = "1")
            @PathVariable Long id
    );
}
