package by.meshicage.controller;

import by.meshicage.controller.doc.TokenControllerDoc;
import by.meshicage.dto.user.UserRequestDto;
import by.meshicage.service.security.TokenService;
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
