package by.meshicage.authenticationservice.service.impl;

import by.meshicage.authenticationservice.dto.UserRequestDto;
import by.meshicage.authenticationservice.dto.UserResponseDto;
import by.meshicage.authenticationservice.service.SecretKeyGenerator;
import by.meshicage.authenticationservice.service.TokenService;
import by.meshicage.authenticationservice.service.UserService;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final UserService userService;
    private final SecretKeyGenerator generator;
    private SecretKey secretKey;

    @Value(value = "${app.token.expireTime:5000}")
    private long expiryTime;

    @PostConstruct
    public void init() {
        secretKey = generator.generateSecretKey();
    }

    @Override
    public String createToken(UserRequestDto userRequestDto) {
        UserResponseDto loginUser  =
                userService.login(userRequestDto.getUsername(), userRequestDto.getPassword());

        return Jwts.builder()
                .subject(loginUser .getUsername())
                .expiration(new Date(System.currentTimeMillis() + expiryTime))
                .claim("role", loginUser .getRole())
                .claim("username", loginUser .getUsername())
                .signWith(secretKey)
                .compact();
    }
}
