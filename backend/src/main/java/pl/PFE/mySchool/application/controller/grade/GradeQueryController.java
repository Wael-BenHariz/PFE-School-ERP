package pl.PFE.mySchool.application.controller.grade;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.PFE.mySchool.domain.model.Grade;
import pl.PFE.mySchool.domain.shared.AccessGuard;
import pl.PFE.mySchool.application.dto.GradeDTO;
import pl.PFE.mySchool.application.handler.grade.GradeQueryHandler;
import pl.PFE.mySchool.application.query.grade.GetGradeByIdQuery;
import pl.PFE.mySchool.application.query.grade.GetRecentGradesQuery;

@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeQueryController {

    private final GradeQueryHandler gradeQueryHandler;
    private final AccessGuard accessGuard;

    @GetMapping("/{id}")
    @Secured({"TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public Grade getGradeById(@PathVariable("id") Long id) {
        accessGuard.checkAccessToGrade(id);
        return gradeQueryHandler.handle(new GetGradeByIdQuery(id));
    }

    @GetMapping("/own")
    @Secured({"STUDENT"})
    public Page<GradeDTO> getRecentGrades(Pageable pageable) {
        return gradeQueryHandler.handle(new GetRecentGradesQuery(pageable));
    }
}
