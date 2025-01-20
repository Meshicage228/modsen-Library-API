package by.meshicage.authenticationservice.service;

import by.meshicage.authenticationservice.dto.CreateUserDto;
import by.meshicage.authenticationservice.dto.UserResponseDto;

public interface UserService {
    UserResponseDto login(String username, String password);
    UserResponseDto register(CreateUserDto createUserDto);
}
