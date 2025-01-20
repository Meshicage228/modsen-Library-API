package by.meshicage.applicationexceptionstarter.decoder;

import by.meshicage.applicationexceptionstarter.dto.ExceptionResponse;
import by.meshicage.applicationexceptionstarter.exception.impl.user.IncorrectCredentialsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

@Slf4j
@RequiredArgsConstructor
public class FeignExceptionDecoder implements ErrorDecoder {
    private final static Map<Predicate<Integer>, Function<ExceptionResponse, Exception>> errorHandlers = new HashMap<>() {{
        put(value -> value == 400, errorMessage -> {
            log.error("Exception message : {}", errorMessage.getMessage());
            return new IncorrectCredentialsException();
        });
    }};
    private final ObjectMapper mapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            ExceptionResponse errorMessage = parseErrorResponse(response);
            return handleError(response.status(), errorMessage);
        } catch (IOException e) {
            String readFailed = "Failed to read error message: " + e.getMessage();
            log.error(readFailed);
            return new RuntimeException(readFailed);
        }
    }

    private ExceptionResponse parseErrorResponse(Response response) throws IOException {
        byte[] arr = response.body().asInputStream().readAllBytes();
        return mapper.readValue(arr, ExceptionResponse.class);
    }

    private Exception handleError(int status, ExceptionResponse errorMessage) {
        return errorHandlers.entrySet().stream()
                .filter(entry -> entry.getKey().test(status))
                .map(entry -> entry.getValue().apply(errorMessage))
                .findFirst()
                .orElseThrow(() -> {
                    String errorReason = Optional.ofNullable(errorMessage.getMessage()).orElse("Unknown error");
                    log.error("No handler for status : {}; exception message : {}", status, errorReason);
                    return new RuntimeException(errorReason);
                });
    }
}