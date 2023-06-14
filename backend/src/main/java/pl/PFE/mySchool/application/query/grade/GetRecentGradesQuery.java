package pl.PFE.mySchool.application.query.grade;


import org.springframework.data.domain.Pageable;

public record GetRecentGradesQuery(Pageable pageable) {
}
