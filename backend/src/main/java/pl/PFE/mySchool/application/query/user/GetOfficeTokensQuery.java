package pl.PFE.mySchool.application.query.user;


import org.springframework.data.domain.Pageable;

public record GetOfficeTokensQuery(Pageable pageable) {
}
