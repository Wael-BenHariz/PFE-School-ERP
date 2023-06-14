package pl.PFE.mySchool.application.handler.activity;

import pl.PFE.mySchool.application.command.activity.ArchiveActivityCommand;
import pl.PFE.mySchool.application.command.activity.CreateActivityCommand;
import pl.PFE.mySchool.application.command.activity.UpdateActivityCommand;

public interface ActivityCommandHandler {
    void handle(CreateActivityCommand command);

    void handle(UpdateActivityCommand command);

    void handle(ArchiveActivityCommand command);
}
