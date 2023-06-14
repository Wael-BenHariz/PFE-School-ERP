package pl.PFE.mySchool.application.handler.user;

import pl.PFE.mySchool.infrastructure.security.JwtResponse;
import pl.PFE.mySchool.application.command.user.LoginCommand;
import pl.PFE.mySchool.application.command.user.RegisterCommand;

public interface AuthHandler {
    JwtResponse handle(LoginCommand command);

    void handle(RegisterCommand command);
}
