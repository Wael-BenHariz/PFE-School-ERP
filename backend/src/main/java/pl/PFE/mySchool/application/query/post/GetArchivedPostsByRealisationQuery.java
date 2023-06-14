package pl.PFE.mySchool.application.query.post;


import org.springframework.data.domain.Pageable;

public record GetArchivedPostsByRealisationQuery(Long realisationId, Pageable pageable) {
}
