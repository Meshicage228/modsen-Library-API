package by.meshicage.authenticationservice.service;

import by.meshicage.authenticationservice.dto.UserRequestDto;

public interface TokenService {
    String createToken(UserRequestDto userRequestDto);
}