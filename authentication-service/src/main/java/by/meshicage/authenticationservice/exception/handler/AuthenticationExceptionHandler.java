package by.meshicage.authenticationservice.exception.handler;

import by.meshicage.authenticationservice.exception.abstr.FailedToCreateResourceException;
import by.meshicage.authenticationservice.exception.dto.ExceptionResponse;
import by.meshicage.authenticationservice.exception.user.IncorrectCredentialsException;
import by.meshicage.authenticationservice.exception.user.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class AuthenticationExceptionHandler {

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FailedToCreateResourceException.class)
    public ExceptionResponse handleResourceNotFoundException(FailedToCreateResourceException e) {
        return buildExceptionResponse(INTERNAL_SERVER_ERROR, e);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ExceptionResponse handleIsbnDuplicate(UserNotFoundException e) {
        return buildExceptionResponse(NOT_FOUND, e);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IncorrectCredentialsException.class)
    public ExceptionResponse handleBookException(IncorrectCredentialsException e) {
        return buildExceptionResponse(BAD_REQUEST, e);
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
