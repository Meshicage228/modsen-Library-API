package by.meshicage.service.security.impl;

import by.meshicage.client.UserClient;
import by.meshicage.dto.user.UserRequestDto;
import by.meshicage.dto.user.UserResponseDto;
import by.meshicage.service.security.SecretKeyGenerator;
import by.meshicage.service.security.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private static final String ROLE_PREFIX = "ROLE_";
    private final UserClient userClient;
    private final SecretKeyGenerator generator;
    private SecretKey secretKey;

    @Value(value = "${app.token.expireTime}")
    private long expiryTime;

    @PostConstruct
    public void init() {
        secretKey = generator.generateSecretKey();
    }

    @Override
    public String createToken(UserRequestDto userRequestDto) {
        UserResponseDto loginUser  =
                userClient.login(userRequestDto.getUsername(), userRequestDto.getPassword());

        return Jwts.builder()
                .subject(loginUser .getUsername())
                .expiration(new Date(System.currentTimeMillis() + expiryTime))
                .claim("role", loginUser.getRole())
                .claim("username", loginUser.getUsername())
                .signWith(secretKey)
                .compact();
    }

    @Override
    public Authentication fromToken(String token) {
        JwtParser parser = Jwts.parser()
                .setSigningKey(secretKey)
                .build();

        Claims payload = (Claims) parser.parse(token).getPayload();

        String username = (String) payload.get("username");
        String role = ROLE_PREFIX + payload.get("role");

        List<SimpleGrantedAuthority> authorities = Arrays.stream(role.split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}
