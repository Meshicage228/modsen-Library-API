package by.meshicage.service.security;

import by.meshicage.dto.user.UserRequestDto;
import org.springframework.security.core.Authentication;

public interface TokenService {
    String createToken(UserRequestDto userRequestDto);
    Authentication fromToken(String token);
}