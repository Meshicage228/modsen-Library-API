package by.meshicage.authenticationservice.mapper;

import by.meshicage.authenticationservice.dto.CreateUserDto;
import by.meshicage.authenticationservice.dto.UserResponseDto;
import by.meshicage.authenticationservice.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Mapping(target = "password", source = "password", qualifiedByName = "getEncodedPassword")
    public abstract UserEntity toEntity(CreateUserDto createUserDto);

    @Named("getEncodedPassword")
    public String encodePassword(String password) {
        return encoder.encode(password);
    }

    @Mapping(target = "role", source = "roleEntity.role")
    public abstract UserResponseDto toDto(UserEntity userEntity);
}
