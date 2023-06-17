package pl.PFE.mySchool.domain.adapter.Scheduel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.command.Scheduel.ArchiveScheduelCommand;
import pl.PFE.mySchool.application.command.Scheduel.CreateScheduelCommand;
import pl.PFE.mySchool.application.command.Scheduel.UpdateScheduelCommand;
import pl.PFE.mySchool.application.handler.Scheduel.ScheduelCommandHandler;
import pl.PFE.mySchool.domain.service.Scheduel.ScheduelCommandService;

@RequiredArgsConstructor
@Component
public class ScheduelCommandHandlerImpl implements ScheduelCommandHandler {

    private final ScheduelCommandService service;

    @Override
    public void handle(CreateScheduelCommand command) {
        service.create(command);
    }

    @Override
    public void handle(ArchiveScheduelCommand command) {
        service.archiveById(command);
    }

    @Override
    public void handle(UpdateScheduelCommand command) {
        service.update(command);
    }
}
