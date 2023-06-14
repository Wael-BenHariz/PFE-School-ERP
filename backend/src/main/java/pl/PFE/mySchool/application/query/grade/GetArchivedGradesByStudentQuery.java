package pl.PFE.mySchool.application.query.grade;


import org.springframework.data.domain.Pageable;

public record GetArchivedGradesByStudentQuery(Long studentId, Pageable pageable) {
}
