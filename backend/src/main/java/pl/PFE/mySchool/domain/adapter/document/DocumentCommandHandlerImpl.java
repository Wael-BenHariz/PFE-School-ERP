package pl.PFE.mySchool.domain.adapter.document;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.command.document.CreateDocumentCommand;
import pl.PFE.mySchool.application.handler.document.DocumentCommandHandler;
import pl.PFE.mySchool.domain.service.document.DocumentCommandService;

@RequiredArgsConstructor
@Component
public class DocumentCommandHandlerImpl implements DocumentCommandHandler {

    private final DocumentCommandService documentCommandService;


    @Override
    public void handle(CreateDocumentCommand command) {documentCommandService.create(command);}
}
