package by.meshicage.authenticationservice.mapper;

import by.meshicage.authenticationservice.dto.UserResponseDto;
import by.meshicage.authenticationservice.entity.RoleEntity;
import by.meshicage.authenticationservice.entity.UserEntity;
import by.meshicage.authenticationservice.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserMapper unit tests")
public class UserMapperTest {
    private static final String USERNAME = "testUser";
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    @Test
    @DisplayName("Map UserEntity to UserResponseDto")
    public void testToDto() {
        // Given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername(USERNAME);
        userEntity.setPassword("encodedPassword");
        userEntity.setRoleEntity(new RoleEntity(1, Role.USER));

        // When
        UserResponseDto userResponseDto = userMapper.toDto(userEntity);

        // Then
        assertNotNull(userResponseDto);
        assertEquals(userEntity.getId(), userResponseDto.getId());
        assertEquals(userEntity.getUsername(), userResponseDto.getUsername());
        assertEquals(userEntity.getPassword(), userResponseDto.getPassword());
        assertNotNull(userResponseDto.getRole());
    }
}