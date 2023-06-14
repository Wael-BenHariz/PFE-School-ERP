package pl.PFE.mySchool.domain.adapter.realisation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.command.realisation.ArchiveRealisationCommand;
import pl.PFE.mySchool.application.command.realisation.CreateRealisationCommand;
import pl.PFE.mySchool.application.command.realisation.UpdateRealisationCommand;
import pl.PFE.mySchool.application.handler.realisation.RealisationCommandHandler;
import pl.PFE.mySchool.domain.service.realisation.RealisationCommandService;

@RequiredArgsConstructor
@Component
public class RealisationCommandHandlerImpl implements RealisationCommandHandler {

    private final RealisationCommandService realisationCommandService;

    @Override
    public void handle(CreateRealisationCommand command) {
        realisationCommandService.create(command);
    }

    @Override
    public void handle(UpdateRealisationCommand command) {
        realisationCommandService.update(command);
    }

    @Override
    public void handle(ArchiveRealisationCommand command) {
        realisationCommandService.archiveById(command);
    }

}
