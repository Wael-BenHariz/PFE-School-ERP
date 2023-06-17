package pl.PFE.mySchool.application.controller.document;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import pl.PFE.mySchool.application.command.document.ArchiveDocumentCommand;
import pl.PFE.mySchool.application.command.document.CreateDocumentCommand;
import pl.PFE.mySchool.application.command.document.UpdateDocumentCommand;
import pl.PFE.mySchool.application.handler.document.DocumentCommandHandler;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentCommandController {
    private final DocumentCommandHandler documentCommandHandler;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createDocument(@Valid @RequestBody CreateDocumentCommand command) {
       documentCommandHandler.handle(command);
    }

    @PutMapping("/{id}")
    public void updateDocument(@PathVariable("id") Long id, @Valid @RequestBody UpdateDocumentCommand command) {
        command.setId(id);
        documentCommandHandler.handle(command);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void archiveById(@PathVariable("id") Long id) {
        documentCommandHandler.handle(new ArchiveDocumentCommand(id));
    }

}
