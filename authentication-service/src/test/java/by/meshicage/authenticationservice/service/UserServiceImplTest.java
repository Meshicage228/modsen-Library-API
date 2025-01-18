package by.meshicage.authenticationservice.service;

import by.meshicage.applicationexceptionstarter.exception.impl.role.RoleNotFountException;
import by.meshicage.applicationexceptionstarter.exception.impl.user.IncorrectCredentialsException;
import by.meshicage.applicationexceptionstarter.exception.impl.user.UserNotFoundException;
import by.meshicage.authenticationservice.dto.CreateUserDto;
import by.meshicage.authenticationservice.dto.UserResponseDto;
import by.meshicage.authenticationservice.entity.RoleEntity;
import by.meshicage.authenticationservice.entity.UserEntity;
import by.meshicage.authenticationservice.enums.Role;
import by.meshicage.authenticationservice.mapper.UserMapper;
import by.meshicage.authenticationservice.repository.RoleRepository;
import by.meshicage.authenticationservice.repository.UserRepository;
import by.meshicage.authenticationservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("UserService unit tests")
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserMapper userMapper;

    @Mock
    private BCryptPasswordEncoder encoder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private static final Role ROLE = Role.USER;
    private static final String TEST_USERNAME = "testUser";
    private static final String TEST_PASSWORD = "testPassword";
    private static final String ENCODED_PASSWORD = "encodedPassword";
    private UserEntity userEntity;
    private CreateUserDto createUserDto;
    private UserResponseDto userResponseDto;
    private RoleEntity roleEntity;

    @BeforeEach
    public void setUp() {
        userEntity = new UserEntity();
        userEntity.setUsername(TEST_USERNAME);
        userEntity.setPassword(ENCODED_PASSWORD);

        createUserDto = CreateUserDto.builder()
                .username(TEST_USERNAME)
                .role(ROLE)
                .password(TEST_PASSWORD).build();

        userResponseDto = UserResponseDto.builder().username(TEST_USERNAME).build();

        roleEntity = new RoleEntity();
        roleEntity.setRole(ROLE);
    }

    @Test
    @DisplayName("Login successfully")
    public void testLoginSuccess() {
        // Given
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(userEntity));
        when(encoder.matches(anyString(), anyString())).thenReturn(true);
        when(userMapper.toDto(any(UserEntity.class))).thenReturn(userResponseDto);

        // When
        UserResponseDto result = userService.login(TEST_USERNAME, TEST_PASSWORD);

        // Then
        assertNotNull(result);
        assertEquals(TEST_USERNAME, result.getUsername());
    }

    @Test
    @DisplayName("Register user successfully")
    public void testRegisterSuccess() {
        // Given
        when(roleRepository.findByRole(any(Role.class))).thenReturn(Optional.of(roleEntity));
        when(userMapper.toEntity(any(CreateUserDto.class))).thenReturn(userEntity);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(userMapper.toDto(any(UserEntity.class))).thenReturn(userResponseDto);

        // When
        UserResponseDto result = userService.register(createUserDto);

        // Then
        assertNotNull(result);
        assertEquals(TEST_USERNAME, result.getUsername());
    }

    @Nested
    @DisplayName("Service exception tests")
    public class ExceptionServiceTests {

        @Test
        @DisplayName("Exception user not found during login")
        public void testLoginUserNotFound() {
            // Given
            when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

            // When / Then
            assertThrows(UserNotFoundException.class, () -> userService.login(TEST_USERNAME, TEST_PASSWORD));
        }

        @Test
        @DisplayName("Exception password is incorrect")
        public void testLoginIncorrectPassword() {
            // Given
            when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(userEntity));
            when(encoder.matches(anyString(), anyString())).thenReturn(false);

            // When / Then
            assertThrows(IncorrectCredentialsException.class, () -> userService.login(TEST_USERNAME, TEST_PASSWORD));
        }

        @Test
        @DisplayName("Exception role not found during registration")
        public void testRegisterRoleNotFound() {
            // Given
            when(roleRepository.findByRole(any(Role.class))).thenReturn(Optional.empty());

            // When / Then
            assertThrows(RoleNotFountException.class, () -> userService.register(createUserDto));
        }
    }
}