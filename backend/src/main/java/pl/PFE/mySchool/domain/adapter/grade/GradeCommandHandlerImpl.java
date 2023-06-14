package pl.PFE.mySchool.domain.adapter.grade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.command.grade.ArchiveGradeCommand;
import pl.PFE.mySchool.application.command.grade.CreateGradeCommand;
import pl.PFE.mySchool.application.command.grade.UpdateGradeCommand;
import pl.PFE.mySchool.application.handler.grade.GradeCommandHandler;
import pl.PFE.mySchool.domain.service.grade.GradeCommandService;

@RequiredArgsConstructor
@Component
public class GradeCommandHandlerImpl implements GradeCommandHandler {

    private final GradeCommandService gradeCommandService;

    @Override
    public void handle(CreateGradeCommand command) {
        gradeCommandService.create(command);
    }

    @Override
    public void handle(UpdateGradeCommand command) {
        gradeCommandService.update(command);
    }

    @Override
    public void handle(ArchiveGradeCommand command) {
        gradeCommandService.archiveById(command);
    }

}
