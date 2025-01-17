package by.meshicage.exception.handler;

import by.meshicage.exception.ResourceNotFoundException;
import by.meshicage.exception.dto.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Slf4j
public class TrackerServiceExceptionHandler {
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionResponse handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error(e.getMessage(), e);
        return ExceptionResponse.builder()
                .status(NOT_FOUND.value())
                .message(e.getMessage())
                .timestamp(LocalDate.now())
                .build();
    }
}
