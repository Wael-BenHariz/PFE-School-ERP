package pl.PFE.mySchool.application.query.realisation;


import org.springframework.data.domain.Pageable;

public record GetActiveRealisationsQuery(Pageable pageable) {
}
