package by.meshicage.authenticationservice.controller;

import by.meshicage.authenticationservice.controller.doc.TokenControllerDoc;
import by.meshicage.authenticationservice.dto.UserRequestDto;
import by.meshicage.authenticationservice.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/token")
@RequiredArgsConstructor
public class TokenController implements TokenControllerDoc {
    private final TokenService tokenService;

    public String createToken(UserRequestDto authenticationData) {
        return tokenService.createToken(authenticationData);
    }
}
