package by.meshicage.authenticationservice.service.impl;

import by.meshicage.authenticationservice.dto.CreateUserDto;
import by.meshicage.authenticationservice.dto.UserResponseDto;
import by.meshicage.authenticationservice.entity.RoleEntity;
import by.meshicage.authenticationservice.entity.UserEntity;
import by.meshicage.authenticationservice.exception.user.FailedToCreateUserException;
import by.meshicage.authenticationservice.exception.user.IncorrectCredentialsException;
import by.meshicage.authenticationservice.exception.role.RoleNotFountException;
import by.meshicage.authenticationservice.exception.user.UserNotFoundException;
import by.meshicage.authenticationservice.mapper.UserMapper;
import by.meshicage.authenticationservice.repository.RoleRepository;
import by.meshicage.authenticationservice.repository.UserRepository;
import by.meshicage.authenticationservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto login(String username, String password) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        if(!encoder.matches(password, userEntity.getPassword())) {
            throw new IncorrectCredentialsException();
        }

        return userMapper.toDto(userEntity);
    }

    @Override
    public UserResponseDto register(CreateUserDto createUserDto) {
        RoleEntity roleEntity = roleRepository.findByRole(createUserDto.getRole())
                .orElseThrow(RoleNotFountException::new);

        UserEntity userEntity = userMapper.toEntity(createUserDto);
        userEntity.setRoleEntity(roleEntity);

        return Optional.of(userRepository.save(userEntity))
                .map(userMapper::toDto)
                .orElseThrow(FailedToCreateUserException::new);
    }
}
