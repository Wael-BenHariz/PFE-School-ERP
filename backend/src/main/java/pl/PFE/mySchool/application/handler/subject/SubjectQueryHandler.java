package pl.PFE.mySchool.application.handler.subject;

import org.springframework.data.domain.Page;
import pl.PFE.mySchool.application.query.subject.GetActiveSubjectsQuery;
import pl.PFE.mySchool.application.query.subject.GetArchivedSubjectsQuery;
import pl.PFE.mySchool.application.query.subject.GetSubjectByIdQuery;
import pl.PFE.mySchool.application.query.subject.SearchSubjectByNameQuery;
import pl.PFE.mySchool.domain.model.Subject;

public interface SubjectQueryHandler {
    Page<Subject> handle(GetActiveSubjectsQuery query);

    Page<Subject> handle(GetArchivedSubjectsQuery query);

    Page<Subject> handle(SearchSubjectByNameQuery query);

    Subject handle(GetSubjectByIdQuery query);

}
