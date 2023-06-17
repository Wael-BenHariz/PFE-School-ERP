package pl.PFE.mySchool.application.controller.document;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.PFE.mySchool.application.handler.document.DocumentQueryHandler;
import pl.PFE.mySchool.application.query.document.GetDocumentByIdQuery;
import pl.PFE.mySchool.application.query.document.GetDocumentsQuery;
import pl.PFE.mySchool.domain.model.Document;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentQueryController {
    private final DocumentQueryHandler documentQueryHandler;

    @GetMapping("/{id}")
    public Document getActivityById(@PathVariable("id") Long id) {
        return documentQueryHandler.handle(new GetDocumentByIdQuery(id));
    }

    @GetMapping
    public Page<Document> getDocuments(Pageable pageable) {
        return documentQueryHandler.handle(new GetDocumentsQuery(pageable));
    }

}
