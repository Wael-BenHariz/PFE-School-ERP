package pl.PFE.mySchool.application.handler.document;

import pl.PFE.mySchool.application.command.document.CreateDocumentCommand;

public interface DocumentCommandHandler {
    void handle(CreateDocumentCommand command);
}
