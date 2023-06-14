package pl.PFE.mySchool.application.query.subject;


import org.springframework.data.domain.Pageable;

public record GetArchivedSubjectsQuery(Pageable pageable) {
}
