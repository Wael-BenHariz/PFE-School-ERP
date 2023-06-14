package pl.PFE.mySchool.domain.adapter.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.command.user.LoginCommand;
import pl.PFE.mySchool.application.command.user.RegisterCommand;
import pl.PFE.mySchool.application.handler.user.AuthHandler;
import pl.PFE.mySchool.domain.service.user.AuthService;
import pl.PFE.mySchool.infrastructure.security.JwtResponse;

@RequiredArgsConstructor
@Component
public class AuthHandlerImpl implements AuthHandler {

    private final AuthService authService;

    @Override
    public JwtResponse handle(LoginCommand command) {
        return authService.login(command);
    }

    @Override
    public void handle(RegisterCommand command) {
        authService.register(command);
    }
}
