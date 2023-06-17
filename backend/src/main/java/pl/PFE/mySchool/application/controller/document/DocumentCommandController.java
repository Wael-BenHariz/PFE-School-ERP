package pl.PFE.mySchool.application.controller.document;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.PFE.mySchool.application.command.document.CreateDocumentCommand;
import pl.PFE.mySchool.application.handler.document.DocumentCommandHandler;
import pl.PFE.mySchool.domain.shared.AccessGuard;

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
}
