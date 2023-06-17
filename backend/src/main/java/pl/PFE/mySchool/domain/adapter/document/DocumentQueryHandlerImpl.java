package pl.PFE.mySchool.domain.adapter.document;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.handler.document.DocumentQueryHandler;
import pl.PFE.mySchool.application.query.document.GetDocumentByIdQuery;
import pl.PFE.mySchool.application.query.document.GetDocumentsQuery;
import pl.PFE.mySchool.domain.model.Document;
import pl.PFE.mySchool.domain.service.document.DocumentQueryService;

@RequiredArgsConstructor
@Component
public class DocumentQueryHandlerImpl implements DocumentQueryHandler {

    private final DocumentQueryService documentQueryService;
    @Override
    public Document handle(GetDocumentByIdQuery query) {
        return documentQueryService.getById(query.id());
    }

    @Override
    public Page<Document> handle(GetDocumentsQuery query) {
        return documentQueryService.getDocuments((Pageable) query.pageable());
    }
}
