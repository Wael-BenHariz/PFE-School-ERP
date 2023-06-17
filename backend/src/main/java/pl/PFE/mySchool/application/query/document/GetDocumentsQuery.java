package pl.PFE.mySchool.application.query.document;


import org.springframework.data.domain.Pageable;

public record GetDocumentsQuery(Pageable pageable) {
}
