package pl.PFE.mySchool.application.handler.level;

import pl.PFE.mySchool.application.command.level.ArchiveLevelCommand;
import pl.PFE.mySchool.application.command.level.CreateLevelCommand;
import pl.PFE.mySchool.application.command.level.UpdateLevelCommand;

public interface LevelCommandHandler {
    void handle(CreateLevelCommand command);

    void handle(UpdateLevelCommand command);

    void handle(ArchiveLevelCommand command);
}
