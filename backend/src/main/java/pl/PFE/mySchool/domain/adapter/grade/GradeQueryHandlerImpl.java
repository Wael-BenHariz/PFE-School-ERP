package pl.PFE.mySchool.domain.adapter.grade;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.handler.grade.GradeQueryHandler;
import pl.PFE.mySchool.domain.model.Grade;
import pl.PFE.mySchool.domain.service.grade.GradeQueryService;
import pl.PFE.mySchool.application.dto.GradeDTO;
import pl.PFE.mySchool.application.query.grade.GetActiveGradesByStudentQuery;
import pl.PFE.mySchool.application.query.grade.GetArchivedGradesByStudentQuery;
import pl.PFE.mySchool.application.query.grade.GetGradeByActivityAndStudentQuery;
import pl.PFE.mySchool.application.query.grade.GetGradeByIdQuery;
import pl.PFE.mySchool.application.query.grade.GetOwnGradesByRealisationQuery;
import pl.PFE.mySchool.application.query.grade.GetRecentGradesQuery;

@RequiredArgsConstructor
@Component
public class GradeQueryHandlerImpl implements GradeQueryHandler {

    private final GradeQueryService gradeQueryService;

    @Override
    public Page<Grade> handle(GetActiveGradesByStudentQuery query) {
        return gradeQueryService.getAllActiveByStudent(query.pageable());
    }

    @Override
    public Page<Grade> handle(GetArchivedGradesByStudentQuery query) {
        return gradeQueryService.getAllArchivedByStudent(query.studentId(), query.pageable());
    }

    @Override
    public Grade handle(GetGradeByActivityAndStudentQuery query) {
        return gradeQueryService.getByActivityAndStudent(query.activityId(), query.studentId());
    }

    @Override
    public Grade handle(GetGradeByIdQuery query) {
        return gradeQueryService.getById(query.id());
    }

    @Override
    public Page<GradeDTO> handle(GetOwnGradesByRealisationQuery query) {
        return gradeQueryService.getOwnGradesByRealisation(query.realisationId(), query.pageable());
    }

    @Override
    public Page<GradeDTO> handle(GetRecentGradesQuery query) {
        return gradeQueryService.getAllActiveByStudent(query.pageable())
                .map(grade -> new GradeDTO(grade.getActivity(), grade));
    }
}
