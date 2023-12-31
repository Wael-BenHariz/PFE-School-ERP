package pl.PFE.mySchool.application.controller.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.PFE.mySchool.application.handler.subject.SubjectQueryHandler;
import pl.PFE.mySchool.application.query.subject.GetActiveSubjectsQuery;
import pl.PFE.mySchool.application.query.subject.GetArchivedSubjectsQuery;
import pl.PFE.mySchool.application.query.subject.GetSubjectByIdQuery;
import pl.PFE.mySchool.application.query.subject.SearchSubjectByNameQuery;
import pl.PFE.mySchool.domain.model.Subject;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectQueryController {

    private final SubjectQueryHandler subjectQueryHandler;

    @GetMapping("/{id}")
    @Secured({"DIRECTOR", "OFFICE", "ADMIN"})
    public Subject getSubjectById(@PathVariable("id") Long id) {
        return subjectQueryHandler.handle(new GetSubjectByIdQuery(id));
    }

    @GetMapping("/search")
    @Secured({"DIRECTOR", "OFFICE", "ADMIN"})
    public Page<Subject> getSubjectByName(@Param("name") String name, Pageable pageable) {
        return subjectQueryHandler.handle(new SearchSubjectByNameQuery(name, pageable));
    }

    @GetMapping
    @Secured({"DIRECTOR", "OFFICE", "ADMIN"})
    public Page<Subject> getActiveSubjects(Pageable pageable) {
        return subjectQueryHandler.handle(new GetActiveSubjectsQuery(pageable));
    }

    @GetMapping("/archived")
    @Secured({"DIRECTOR", "OFFICE", "ADMIN"})
    public Page<Subject> getArchivedSubjects(Pageable pageable) {
        return subjectQueryHandler.handle(new GetArchivedSubjectsQuery(pageable));
    }
}
