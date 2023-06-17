package pl.PFE.mySchool.application.handler.document;

import pl.PFE.mySchool.application.command.document.ArchiveDocumentCommand;
import pl.PFE.mySchool.application.command.document.CreateDocumentCommand;
import pl.PFE.mySchool.application.command.document.UpdateDocumentCommand;

public interface DocumentCommandHandler {
    void handle(CreateDocumentCommand command);
    void handle(ArchiveDocumentCommand command);
    void handle(UpdateDocumentCommand command);
}
