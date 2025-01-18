package by.meshicage.config;

import by.meshicage.exception.decoder.FeignExceptionDecoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageServiceApplicationConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ErrorDecoder feignExceptionDecoder(ObjectMapper objectMapper){
        return new FeignExceptionDecoder(objectMapper.registerModule(new JavaTimeModule()));
    }
}
