package by.meshicage.applicationexceptionstarter.configuration;

import by.meshicage.applicationexceptionstarter.decoder.FeignExceptionDecoder;
import by.meshicage.applicationexceptionstarter.handler.ApplicationExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@AutoConfiguration
@Slf4j
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class ExceptionHandlerConfig {

    @PostConstruct
    public void init() {
        log.info("Initializing ExceptionHandlerStarter Configuration");
    }

    @Bean
    public ErrorDecoder feignExceptionDecoder(ObjectMapper objectMapper){
        return new FeignExceptionDecoder(objectMapper);
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler extends ApplicationExceptionHandler {

    }
}
