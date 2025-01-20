package by.meshicage.client;

import by.meshicage.dto.user.UserResponseDto;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "${app.clients.user-client.name}",
        url = "${app.clients.user-client.url}",
        path = "${app.clients.user-client.path}"
)
public interface UserClient {

    @GetMapping("/login")
    UserResponseDto login(@RequestParam(value = "username") @NotBlank(message = "Provide login") String username,
                            @RequestParam(value = "password") @NotBlank(message = "Provide password") String password);
}
