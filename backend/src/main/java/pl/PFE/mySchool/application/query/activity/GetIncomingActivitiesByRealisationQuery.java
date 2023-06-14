package pl.PFE.mySchool.application.query.activity;


import org.springframework.data.domain.Pageable;

public record GetIncomingActivitiesByRealisationQuery(Long realisationId, Pageable pageable) {
}
