package pl.PFE.mySchool.domain.adapter.level;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.command.level.ArchiveLevelCommand;
import pl.PFE.mySchool.application.command.level.CreateLevelCommand;
import pl.PFE.mySchool.application.command.level.UpdateLevelCommand;
import pl.PFE.mySchool.application.handler.level.LevelCommandHandler;
import pl.PFE.mySchool.domain.service.level.LevelCommandService;

@RequiredArgsConstructor
@Component
public class LevelCommandHandlerImpl implements LevelCommandHandler {

    private final LevelCommandService levelCommandService;

    @Override
    public void handle(CreateLevelCommand command) {
        levelCommandService.create(command);
    }

    @Override
    public void handle(UpdateLevelCommand command) {
        levelCommandService.update(command);
    }

    @Override
    public void handle(ArchiveLevelCommand command) {
        levelCommandService.archiveById(command);
    }

}
