package pl.PFE.mySchool.domain.adapter.activity;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.dto.ActivityDTO;
import pl.PFE.mySchool.application.handler.activity.ActivityQueryHandler;
import pl.PFE.mySchool.application.query.grade.GetGradesOfActivityQuery;
import pl.PFE.mySchool.domain.exception.grade.GradeNotFoundException;
import pl.PFE.mySchool.domain.model.Activity;
import pl.PFE.mySchool.domain.model.Grade;
import pl.PFE.mySchool.domain.model.User;
import pl.PFE.mySchool.domain.service.activity.ActivityQueryService;
import pl.PFE.mySchool.domain.service.grade.GradeQueryService;
import pl.PFE.mySchool.application.dto.GradeOfActivityDTO;
import pl.PFE.mySchool.application.query.activity.GetActiveActivitiesByRealisationQuery;
import pl.PFE.mySchool.application.query.activity.GetActivityByIdQuery;
import pl.PFE.mySchool.application.query.activity.GetArchivedActivitiesByRealisationQuery;
import pl.PFE.mySchool.application.query.activity.GetIncomingActivitiesByRealisationQuery;
import pl.PFE.mySchool.application.query.activity.GetIncomingActivitiesQuery;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ActivityQueryHandlerImpl implements ActivityQueryHandler {

    private final ActivityQueryService activityQueryService;
    private final GradeQueryService gradeQueryService;

    @Override
    public Page<ActivityDTO> handle(GetActiveActivitiesByRealisationQuery query) {
        return activityQueryService.getAllActiveByRealisation(query.realisationId(), query.pageable());
    }

    @Override
    public Page<Activity> handle(GetArchivedActivitiesByRealisationQuery query) {
        return activityQueryService.getAllArchivedByRealisation(query.realisationId(), query.pageable());
    }

    @Override
    public Activity handle(GetActivityByIdQuery query) {
        return activityQueryService.getById(query.id());
    }

    @Override
    public Page<ActivityDTO> handle(GetIncomingActivitiesByRealisationQuery query) {
        return activityQueryService.getAllIncomingByRealisation(query.realisationId(), query.pageable());
    }

    @Override
    public List<GradeOfActivityDTO> handle(GetGradesOfActivityQuery query) {
        List<GradeOfActivityDTO> gradesOfActivity = activityQueryService.getGradesOfActivity(query.activityId(), query.pageable());
        gradesOfActivity.forEach((gradeOfActivityDTO -> {
            try {
                Grade grade = gradeQueryService.getByActivityAndStudent(gradeOfActivityDTO.getActivityId(), gradeOfActivityDTO.getStudentId());
                gradeOfActivityDTO.setGrade(grade.getValue());
                gradeOfActivityDTO.setComment(grade.getComment());
                gradeOfActivityDTO.setCreatedAt(grade.getCreatedAt());
                gradeOfActivityDTO.setUpdatedAt(grade.getUpdatedAt());
            } catch (GradeNotFoundException ignored) {
            }
        }));
        return gradesOfActivity;
    }

    @Override
    public Page<ActivityDTO> handle(GetIncomingActivitiesQuery query) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return activityQueryService.getAllIncomingByStudent(user.getId(), query.pageable());
    }
}
