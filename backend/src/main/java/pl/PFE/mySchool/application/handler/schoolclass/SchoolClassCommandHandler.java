package pl.PFE.mySchool.application.handler.schoolclass;

import pl.PFE.mySchool.application.command.schoolclass.ArchiveSchoolClassCommand;
import pl.PFE.mySchool.application.command.schoolclass.CreateSchoolClassCommand;
import pl.PFE.mySchool.application.command.schoolclass.UpdateSchoolClassCommand;

public interface SchoolClassCommandHandler {
    void handle(CreateSchoolClassCommand command);

    void handle(UpdateSchoolClassCommand command);

    void handle(ArchiveSchoolClassCommand command);
}
