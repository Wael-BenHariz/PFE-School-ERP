package pl.PFE.mySchool.domain.adapter.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.handler.user.UserCommandHandler;
import pl.PFE.mySchool.domain.service.user.UserCommandService;
import pl.PFE.mySchool.application.command.user.ArchiveUserCommand;
import pl.PFE.mySchool.application.command.user.AssignCommand;
import pl.PFE.mySchool.application.command.user.ChangePasswordCommand;
import pl.PFE.mySchool.application.command.user.GenerateRegistrationTokensCommand;
import pl.PFE.mySchool.application.command.user.UnassignCommand;
import pl.PFE.mySchool.application.command.user.UpdateDescriptionCommand;
import pl.PFE.mySchool.application.command.user.UpdateProfileImageCommand;
import pl.PFE.mySchool.application.command.user.UpdateUserCommand;
import pl.PFE.mySchool.domain.model.RegistrationToken;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserCommandHandlerImpl implements UserCommandHandler {

    private final UserCommandService userCommandService;


    @Override
    public void handle(UpdateUserCommand command) {
        userCommandService.update(command);
    }

    @Override
    public void handle(ArchiveUserCommand command) {
        userCommandService.archiveById(command);
    }

    @Override
    public List<RegistrationToken> handle(GenerateRegistrationTokensCommand command) {
        return userCommandService.generateRegistrationTokens(command);
    }

    @Override
    public void handle(UpdateDescriptionCommand command) {
        userCommandService.updateDescription(command);
    }

    @Override
    public void handle(ChangePasswordCommand command) {
        userCommandService.changePassword(command);
    }

    @Override
    public void handle(UnassignCommand command) {
        userCommandService.unassignStudent(command);
    }

    @Override
    public void handle(AssignCommand command) {
        userCommandService.assignStudent(command);
    }

    @Override
    public void handle(UpdateProfileImageCommand command) {
        userCommandService.updateProfileImage(command);
    }
}
