package by.meshicage.service.security;

import javax.crypto.SecretKey;

public interface SecretKeyGenerator {
    SecretKey generateSecretKey();
}