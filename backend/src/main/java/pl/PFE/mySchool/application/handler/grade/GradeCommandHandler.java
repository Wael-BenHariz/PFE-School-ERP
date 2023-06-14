package pl.PFE.mySchool.application.handler.grade;

import pl.PFE.mySchool.application.command.grade.ArchiveGradeCommand;
import pl.PFE.mySchool.application.command.grade.CreateGradeCommand;
import pl.PFE.mySchool.application.command.grade.UpdateGradeCommand;

public interface GradeCommandHandler {
    void handle(CreateGradeCommand command);

    void handle(UpdateGradeCommand command);

    void handle(ArchiveGradeCommand command);
}
