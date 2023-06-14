package pl.PFE.mySchool.domain.adapter.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.handler.subject.SubjectQueryHandler;
import pl.PFE.mySchool.application.query.subject.GetActiveSubjectsQuery;
import pl.PFE.mySchool.application.query.subject.GetArchivedSubjectsQuery;
import pl.PFE.mySchool.application.query.subject.GetSubjectByIdQuery;
import pl.PFE.mySchool.application.query.subject.SearchSubjectByNameQuery;
import pl.PFE.mySchool.domain.service.subject.SubjectQueryService;
import pl.PFE.mySchool.domain.model.Subject;

@RequiredArgsConstructor
@Component
public class SubjectQueryHandlerImpl implements SubjectQueryHandler {

    private final SubjectQueryService subjectQueryService;

    @Override
    public Page<Subject> handle(GetActiveSubjectsQuery query) {
        return subjectQueryService.getAllActive(query.pageable());
    }

    @Override
    public Page<Subject> handle(GetArchivedSubjectsQuery query) {
        return subjectQueryService.getAllArchived(query.pageable());
    }

    @Override
    public Page<Subject> handle(SearchSubjectByNameQuery query) {
        return subjectQueryService.getByNameContaining(query.name(), query.pageable());
    }

    @Override
    public Subject handle(GetSubjectByIdQuery query) {
        return subjectQueryService.getById(query.id());
    }
}
