package pl.PFE.mySchool.application.handler.document;
import org.springframework.data.domain.Page;
import pl.PFE.mySchool.application.query.document.GetDocumentByIdQuery;
import pl.PFE.mySchool.application.query.document.GetDocumentsQuery;
import pl.PFE.mySchool.domain.model.Document;

public interface DocumentQueryHandler {
    Document handle(GetDocumentByIdQuery query);
    Page<Document> handle(GetDocumentsQuery query);
}
