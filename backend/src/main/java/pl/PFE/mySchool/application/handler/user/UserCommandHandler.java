package pl.PFE.mySchool.application.handler.user;

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

public interface UserCommandHandler {

    void handle(UpdateUserCommand command);

    void handle(ArchiveUserCommand command);

    List<RegistrationToken> handle(GenerateRegistrationTokensCommand command);

    void handle(UpdateDescriptionCommand command);

    void handle(ChangePasswordCommand command);

    void handle(UnassignCommand unassignCommand);

    void handle(AssignCommand command);

    void handle(UpdateProfileImageCommand updateProfileImageCommand);
}
