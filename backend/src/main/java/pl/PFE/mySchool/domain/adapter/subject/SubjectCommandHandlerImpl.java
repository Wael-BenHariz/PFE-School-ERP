package pl.PFE.mySchool.domain.adapter.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.command.subject.ArchiveSubjectCommand;
import pl.PFE.mySchool.application.command.subject.CreateSubjectCommand;
import pl.PFE.mySchool.application.command.subject.UpdateSubjectCommand;
import pl.PFE.mySchool.application.command.subject.UpdateSubjectImageCommand;
import pl.PFE.mySchool.application.handler.subject.SubjectCommandHandler;
import pl.PFE.mySchool.domain.service.subject.SubjectCommandService;

@RequiredArgsConstructor
@Component
public class SubjectCommandHandlerImpl implements SubjectCommandHandler {

    private final SubjectCommandService subjectCommandService;

    @Override
    public void handle(CreateSubjectCommand command) {
        subjectCommandService.create(command);
    }

    @Override
    public void handle(UpdateSubjectCommand command) {
        subjectCommandService.update(command);
    }

    @Override
    public void handle(ArchiveSubjectCommand command) {
        subjectCommandService.archiveById(command);
    }

    @Override
    public void handle(UpdateSubjectImageCommand command) {
        subjectCommandService.updateSubjectImage(command);
    }

}
