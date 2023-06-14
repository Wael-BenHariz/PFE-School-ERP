package pl.PFE.mySchool.application.handler.subject;

import pl.PFE.mySchool.application.command.subject.ArchiveSubjectCommand;
import pl.PFE.mySchool.application.command.subject.CreateSubjectCommand;
import pl.PFE.mySchool.application.command.subject.UpdateSubjectCommand;
import pl.PFE.mySchool.application.command.subject.UpdateSubjectImageCommand;

public interface SubjectCommandHandler {
    void handle(CreateSubjectCommand command);

    void handle(UpdateSubjectCommand command);

    void handle(ArchiveSubjectCommand command);

    void handle(UpdateSubjectImageCommand command);
}
