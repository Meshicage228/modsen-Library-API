package by.meshicage.authenticationservice.service.impl;

import by.meshicage.authenticationservice.service.SecretKeyGenerator;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class SecretKeyGeneratorHS256 implements SecretKeyGenerator {
    @Override
    public SecretKey generateSecretKey() {
        return Jwts.SIG.HS256.key().build();
    }
}