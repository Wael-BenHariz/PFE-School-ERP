package pl.PFE.mySchool.application.handler.schoolclass;

import org.springframework.data.domain.Page;
import pl.PFE.mySchool.domain.model.SchoolClass;
import pl.PFE.mySchool.application.query.schoolclass.GetActiveSchoolClassesQuery;
import pl.PFE.mySchool.application.query.schoolclass.GetArchivedSchoolClassesQuery;
import pl.PFE.mySchool.application.query.schoolclass.GetSchoolClassByIdQuery;

public interface SchoolClassQueryHandler {
    Page<SchoolClass> handle(GetActiveSchoolClassesQuery query);

    Page<SchoolClass> handle(GetArchivedSchoolClassesQuery query);

    SchoolClass handle(GetSchoolClassByIdQuery query);
}
