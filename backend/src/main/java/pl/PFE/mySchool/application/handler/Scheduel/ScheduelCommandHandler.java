package pl.PFE.mySchool.application.handler.Scheduel;

import pl.PFE.mySchool.application.command.Scheduel.ArchiveScheduelCommand;
import pl.PFE.mySchool.application.command.Scheduel.CreateScheduelCommand;
import pl.PFE.mySchool.application.command.Scheduel.UpdateScheduelCommand;

public interface ScheduelCommandHandler {

    void handle(CreateScheduelCommand command);

    void handle(ArchiveScheduelCommand command);

    void handle(UpdateScheduelCommand command);
}
