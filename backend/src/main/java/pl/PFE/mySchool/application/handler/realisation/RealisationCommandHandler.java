package pl.PFE.mySchool.application.handler.realisation;

import pl.PFE.mySchool.application.command.realisation.ArchiveRealisationCommand;
import pl.PFE.mySchool.application.command.realisation.CreateRealisationCommand;
import pl.PFE.mySchool.application.command.realisation.UpdateRealisationCommand;

public interface RealisationCommandHandler {
    void handle(CreateRealisationCommand command);

    void handle(UpdateRealisationCommand command);

    void handle(ArchiveRealisationCommand command);
}
