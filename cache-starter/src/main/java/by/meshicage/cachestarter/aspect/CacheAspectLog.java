package by.meshicage.cachestarter.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class CacheAspectLog {

    @Pointcut("@annotation(org.springframework.cache.annotation.Cacheable)")
    public void cacheableMethods() {}

    @Pointcut("@annotation(org.springframework.cache.annotation.CachePut)")
    public void cachePutMethods() {}

    @Pointcut("@annotation(org.springframework.cache.annotation.CacheEvict)")
    public void cacheEvictMethods() {}

    @Before("cacheableMethods() || cachePutMethods() || cacheEvictMethods()")
    public void beforeCacheAnnotations(JoinPoint joinPoint) {
        log.info("Attempting to enter method : {}", joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "cachePutMethods()", returning = "object")
    public void afterCachePutMethods(JoinPoint joinPoint, Object object) {
        log.info("Updated cache from method : {} returning object : {}", joinPoint.getSignature().getName(), object);
    }

    @AfterReturning("cacheEvictMethods()")
    public void afterCacheEvictMethods(JoinPoint joinPoint) {
        log.info("Deleted cache from method : {}", joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "cacheableMethods()", returning = "object")
    public void afterCacheableMethods(JoinPoint joinPoint, Object object) {
        log.info("Created cache from method : {} returning object : {}", joinPoint.getSignature().getName(), object);
    }
}
