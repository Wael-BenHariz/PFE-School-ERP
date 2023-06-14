package pl.PFE.mySchool.domain.adapter.activity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.command.activity.ArchiveActivityCommand;
import pl.PFE.mySchool.application.command.activity.CreateActivityCommand;
import pl.PFE.mySchool.application.command.activity.UpdateActivityCommand;
import pl.PFE.mySchool.application.handler.activity.ActivityCommandHandler;
import pl.PFE.mySchool.domain.service.activity.ActivityCommandService;

@RequiredArgsConstructor
@Component
public class ActivityCommandHandlerImpl implements ActivityCommandHandler {

    private final ActivityCommandService activityCommandService;

    @Override
    public void handle(CreateActivityCommand command) {
        activityCommandService.create(command);
    }

    @Override
    public void handle(UpdateActivityCommand command) {
        activityCommandService.update(command);
    }

    @Override
    public void handle(ArchiveActivityCommand command) {
        activityCommandService.archiveById(command);
    }

}
