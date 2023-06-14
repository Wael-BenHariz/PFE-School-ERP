package pl.PFE.mySchool.application.handler.activity;

import org.springframework.data.domain.Page;
import pl.PFE.mySchool.application.dto.ActivityDTO;
import pl.PFE.mySchool.application.query.activity.GetActivityByIdQuery;
import pl.PFE.mySchool.application.query.activity.GetArchivedActivitiesByRealisationQuery;
import pl.PFE.mySchool.application.query.activity.GetIncomingActivitiesByRealisationQuery;
import pl.PFE.mySchool.domain.model.Activity;
import pl.PFE.mySchool.application.dto.GradeOfActivityDTO;
import pl.PFE.mySchool.application.query.activity.GetActiveActivitiesByRealisationQuery;
import pl.PFE.mySchool.application.query.activity.GetIncomingActivitiesQuery;
import pl.PFE.mySchool.application.query.grade.GetGradesOfActivityQuery;

import java.util.List;

public interface ActivityQueryHandler {
    Page<ActivityDTO> handle(GetActiveActivitiesByRealisationQuery query);

    Page<Activity> handle(GetArchivedActivitiesByRealisationQuery query);

    Activity handle(GetActivityByIdQuery query);

    Page<ActivityDTO> handle(GetIncomingActivitiesByRealisationQuery getIncomingActivitiesByRealisationQuery);

    List<GradeOfActivityDTO> handle(GetGradesOfActivityQuery getGradesOfActivityQuery);

    Page<ActivityDTO> handle(GetIncomingActivitiesQuery getIncomingActivitiesQuery);
}
