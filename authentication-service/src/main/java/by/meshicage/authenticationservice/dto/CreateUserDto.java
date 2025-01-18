package by.meshicage.authenticationservice.dto;

import by.meshicage.authenticationservice.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    @NotBlank(message = "Please provide a username")
    @Size(message = "Username must be between 3 and 100 characters", min = 3, max = 100)
    private String username;

    @NotBlank(message = "Please provide a password")
    @Size(message = "Password must be between 4 and 16 characters", min = 4, max = 16)
    private String password;

    @NotNull(message = "Please specify a role")
    private Role role;
}
