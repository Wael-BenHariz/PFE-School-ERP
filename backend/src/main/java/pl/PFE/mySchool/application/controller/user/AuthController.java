package pl.PFE.mySchool.application.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.PFE.mySchool.infrastructure.security.JwtResponse;
import pl.PFE.mySchool.application.command.user.LoginCommand;
import pl.PFE.mySchool.application.command.user.RegisterCommand;
import pl.PFE.mySchool.application.handler.user.AuthHandler;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthHandler authHandler;

    @PostMapping("/authorize")
    public JwtResponse login(@Valid @RequestBody LoginCommand command) {
        return authHandler.handle(command);
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterCommand command) {
        authHandler.handle(command);
    }
}
