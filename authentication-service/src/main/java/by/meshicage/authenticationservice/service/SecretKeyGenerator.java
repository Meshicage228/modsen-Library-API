package by.meshicage.authenticationservice.service;

import javax.crypto.SecretKey;

public interface SecretKeyGenerator {
    SecretKey generateSecretKey();
}