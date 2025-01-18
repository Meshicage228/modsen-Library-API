package by.meshicage.service.security.impl;

import by.meshicage.service.security.SecretKeyGenerator;
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