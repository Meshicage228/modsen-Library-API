package by.meshicage.authenticationservice.controller;

import by.meshicage.authenticationservice.controller.doc.UserControllerDoc;
import by.meshicage.authenticationservice.dto.CreateUserDto;
import by.meshicage.authenticationservice.dto.UserResponseDto;
import by.meshicage.authenticationservice.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController implements UserControllerDoc {
    private final UserService userService;

    public UserResponseDto createUser(@RequestBody @Valid CreateUserDto createUserDto) {
        return userService.register(createUserDto);
    }

    public UserResponseDto getUser(@RequestParam(value = "username") @NotBlank(message = "Provide login") String username,
                                   @RequestParam(value = "password") @NotBlank(message = "Provide password") String password) {
        return userService.login(username, password);
    }
}
