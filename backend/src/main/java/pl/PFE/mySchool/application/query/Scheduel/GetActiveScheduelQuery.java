package pl.PFE.mySchool.application.query.Scheduel;

import org.springframework.data.domain.Pageable;

public record GetActiveScheduelQuery(Pageable pageable) {
}
