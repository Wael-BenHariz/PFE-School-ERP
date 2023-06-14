package pl.PFE.mySchool.domain.adapter.schoolclass;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.handler.schoolclass.SchoolClassQueryHandler;
import pl.PFE.mySchool.application.query.schoolclass.GetActiveSchoolClassesQuery;
import pl.PFE.mySchool.application.query.schoolclass.GetArchivedSchoolClassesQuery;
import pl.PFE.mySchool.application.query.schoolclass.GetSchoolClassByIdQuery;
import pl.PFE.mySchool.domain.model.SchoolClass;
import pl.PFE.mySchool.domain.service.schoolclass.SchoolClassQueryService;

@RequiredArgsConstructor
@Component
public class SchoolClassQueryHandlerImpl implements SchoolClassQueryHandler {

    private final SchoolClassQueryService schoolClassQueryService;

    @Override
    public Page<SchoolClass> handle(GetActiveSchoolClassesQuery query) {
        return schoolClassQueryService.getAllActive(query.pageable());
    }

    @Override
    public Page<SchoolClass> handle(GetArchivedSchoolClassesQuery query) {
        return schoolClassQueryService.getAllArchived(query.pageable());
    }

    @Override
    public SchoolClass handle(GetSchoolClassByIdQuery query) {
        return schoolClassQueryService.getById(query.id());
    }
}
