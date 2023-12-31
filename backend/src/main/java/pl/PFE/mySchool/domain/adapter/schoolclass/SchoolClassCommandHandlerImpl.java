package pl.PFE.mySchool.domain.adapter.schoolclass;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.command.schoolclass.ArchiveSchoolClassCommand;
import pl.PFE.mySchool.application.command.schoolclass.CreateSchoolClassCommand;
import pl.PFE.mySchool.application.command.schoolclass.UpdateSchoolClassCommand;
import pl.PFE.mySchool.application.handler.schoolclass.SchoolClassCommandHandler;
import pl.PFE.mySchool.domain.service.schoolclass.SchoolClassCommandService;

@RequiredArgsConstructor
@Component
public class SchoolClassCommandHandlerImpl implements SchoolClassCommandHandler {

    private final SchoolClassCommandService schoolClassCommandService;

    @Override
    public void handle(CreateSchoolClassCommand command) {
        schoolClassCommandService.create(command);
    }

    @Override
    public void handle(UpdateSchoolClassCommand command) {
        schoolClassCommandService.update(command);
    }

    @Override
    public void handle(ArchiveSchoolClassCommand command) {
        schoolClassCommandService.archiveById(command);
    }

}
