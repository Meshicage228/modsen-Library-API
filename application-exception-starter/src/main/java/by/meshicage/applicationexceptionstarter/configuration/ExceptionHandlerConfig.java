package by.meshicage.applicationexceptionstarter.configuration;

import by.meshicage.applicationexceptionstarter.decoder.FeignExceptionDecoder;
import by.meshicage.applicationexceptionstarter.handler.ApplicationExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@AutoConfiguration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class ExceptionHandlerConfig {

    @Bean
    public ErrorDecoder feignExceptionDecoder(ObjectMapper objectMapper){
        return new FeignExceptionDecoder(objectMapper);
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler extends ApplicationExceptionHandler {

    }
}
