package by.meshicage.authenticationservice.dto;

import by.meshicage.authenticationservice.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String username;
    private String password;
    private Role role;
}
