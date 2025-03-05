package by.meshicage.cachestarter.config;

import by.meshicage.cachestarter.aspect.CacheAspectLog;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@ConditionalOnProperty(prefix = "app.redis", name = "enabled", havingValue = "true", matchIfMissing = true)
public class CacheStarterConfiguration {

    @PostConstruct
    public void init() {
        log.info("Initializing RedisCacheStarter Configuration");
    }

    @Bean
    public RedisCacheConfiguration defaultCacheConfig(CacheProperties properties, ObjectMapper objectMapper) {
        CacheProperties.Redis redisProperties = properties.getRedis();
        ObjectMapper redisObjectMapper = objectMapper.copy()
                .activateDefaultTyping(
                        objectMapper.getPolymorphicTypeValidator(),
                        ObjectMapper.DefaultTyping.NON_FINAL,
                        JsonTypeInfo.As.PROPERTY
                );
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Optional.ofNullable(redisProperties.getTimeToLive()).orElse(Duration.ZERO))
                .prefixCacheNameWith(Optional.ofNullable(redisProperties.getKeyPrefix()).orElse(""))
                .serializeKeysWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer(redisObjectMapper)));
    }

    @Bean
    public CacheAspectLog cacheAspectLog() {
        return new CacheAspectLog();
    }
}
