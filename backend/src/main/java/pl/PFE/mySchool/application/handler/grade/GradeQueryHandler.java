package pl.PFE.mySchool.application.handler.grade;

import org.springframework.data.domain.Page;
import pl.PFE.mySchool.application.query.grade.GetActiveGradesByStudentQuery;
import pl.PFE.mySchool.domain.model.Grade;
import pl.PFE.mySchool.application.dto.GradeDTO;
import pl.PFE.mySchool.application.query.grade.GetArchivedGradesByStudentQuery;
import pl.PFE.mySchool.application.query.grade.GetGradeByActivityAndStudentQuery;
import pl.PFE.mySchool.application.query.grade.GetGradeByIdQuery;
import pl.PFE.mySchool.application.query.grade.GetOwnGradesByRealisationQuery;
import pl.PFE.mySchool.application.query.grade.GetRecentGradesQuery;

public interface GradeQueryHandler {
    Page<Grade> handle(GetActiveGradesByStudentQuery query);

    Page<Grade> handle(GetArchivedGradesByStudentQuery query);

    Grade handle(GetGradeByActivityAndStudentQuery query);

    Grade handle(GetGradeByIdQuery query);

    Page<GradeDTO> handle(GetOwnGradesByRealisationQuery getOwnGradesByRealisationQuery);

    Page<GradeDTO> handle(GetRecentGradesQuery getRecentGradesQuery);
}
