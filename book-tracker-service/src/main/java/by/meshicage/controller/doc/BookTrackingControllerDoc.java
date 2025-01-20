package by.meshicage.controller.doc;


import by.meshicage.applicationexceptionstarter.dto.ExceptionResponse;
import by.meshicage.dto.CreateBookTracking;
import by.meshicage.dto.CreatedBookTracking;
import by.meshicage.dto.UpdateBookTrackingStatus;
import by.meshicage.dto.UpdatedBookTracking;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

public interface BookTrackingControllerDoc {

    @Operation(summary = "Create a new book tracking entry",
            description = "This endpoint allows you to create a new tracking entry for a book. " +
                    "You must provide the necessary details such as book ID and tracking status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the book tracking entry"),
            @ApiResponse(responseCode = "400", description = "Invalid data for creating the book tracking entry",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping
    @ResponseStatus(CREATED)
    CreatedBookTracking createBookTracking(
            @Parameter(description = "Data for creating a new book tracking entry")
            @RequestBody CreateBookTracking createBookTracking
    );

    @Operation(summary = "Get available book tracking entries",
            description = "Retrieve a paginated list of available book tracking entries.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved available book tracking entries")
    })
    @GetMapping("/available")
    Page<CreatedBookTracking> getBookTrackingAvailable(
            @Parameter(description = "Page number to retrieve", example = "0")
            @RequestParam(required = false, defaultValue = "0") Integer pageNum,
            @Parameter(description = "Number of entries per page", example = "100")
            @RequestParam(required = false, defaultValue = "100") Integer pageSize
    );

    @Operation(summary = "Update book tracking status",
            description = "Update the tracking status of a book using its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the book tracking status"),
            @ApiResponse(responseCode = "404", description = "Book tracking entry not found",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data for updating the book tracking status",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PatchMapping
    UpdatedBookTracking updateBookTrackingStatus(
            @Parameter(description = "Data for updating the book tracking status")
            @RequestBody UpdateBookTrackingStatus bookTrackingStatus
    );

    @Operation(summary = "Delete a book tracking entry by book ID",
            description = "Remove a book tracking entry from the system using its book ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the book tracking entry"),
            @ApiResponse(responseCode = "404", description = "Book tracking entry not found",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @DeleteMapping("/{bookId}")
    @ResponseStatus(NO_CONTENT)
    void deleteBookTracking(
            @Parameter(description = "ID of the book to delete tracking entry", example = "1")
            @PathVariable Long bookId
    );
}
