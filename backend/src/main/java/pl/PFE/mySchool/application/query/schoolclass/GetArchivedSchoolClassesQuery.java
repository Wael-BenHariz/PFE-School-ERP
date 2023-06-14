package pl.PFE.mySchool.application.query.schoolclass;


import org.springframework.data.domain.Pageable;

public record GetArchivedSchoolClassesQuery(Pageable pageable) {
}
