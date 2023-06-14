package pl.PFE.mySchool.application.query.level;


import org.springframework.data.domain.Pageable;

public record GetArchivedLevelsQuery(Pageable pageable) {
}
