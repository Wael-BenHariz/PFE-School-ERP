package pl.PFE.mySchool.application.query.realisation;


import org.springframework.data.domain.Pageable;

public record GetArchivedRealisationsQuery(Pageable pageable) {
}
