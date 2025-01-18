package by.meshicage.applicationexceptionstarter.handler;

import by.meshicage.applicationexceptionstarter.dto.ExceptionResponse;
import by.meshicage.applicationexceptionstarter.exception.abstr.BookException;
import by.meshicage.applicationexceptionstarter.exception.abstr.FailedToCreateResourceException;
import by.meshicage.applicationexceptionstarter.exception.abstr.ResourceNotFoundException;
import by.meshicage.applicationexceptionstarter.exception.impl.book.DuplicateIsbnException;
import by.meshicage.applicationexceptionstarter.exception.impl.user.IncorrectCredentialsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

import static org.springframework.http.HttpStatus.*;

@Slf4j
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionResponse handleResourceNotFoundException(ResourceNotFoundException e) {
        return buildExceptionResponse(NOT_FOUND, e);
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(DuplicateIsbnException.class)
    public ExceptionResponse handleIsbnDuplicate(DuplicateIsbnException e) {
        return buildExceptionResponse(CONFLICT, e);
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FailedToCreateResourceException.class)
    public ExceptionResponse handleResourceNotFoundException(FailedToCreateResourceException e) {
        return buildExceptionResponse(INTERNAL_SERVER_ERROR, e);
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BookException.class)
    public ExceptionResponse handleBookException(BookException e) {
        return buildExceptionResponse(INTERNAL_SERVER_ERROR, e);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IncorrectCredentialsException.class)
    public ExceptionResponse incorrectCredentialsHandling(IncorrectCredentialsException e) {
        return buildExceptionResponse(BAD_REQUEST, e);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        String defaultMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error(defaultMessage);

        ExceptionResponse response = ExceptionResponse.builder()
                .status(BAD_REQUEST.value())
                .message(defaultMessage)
                .timestamp(LocalDate.now())
                .build();

        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    private ExceptionResponse buildExceptionResponse(HttpStatus status, Exception e) {
        log.error(e.getMessage(), e);
        return ExceptionResponse.builder()
                .status(status.value())
                .message(e.getMessage())
                .timestamp(LocalDate.now())
                .build();
    }
}