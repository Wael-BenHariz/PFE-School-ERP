package pl.PFE.mySchool.application.handler.Scheduel;

import org.springframework.data.domain.Page;
import pl.PFE.mySchool.application.query.Scheduel.GetActiveScheduelQuery;
import pl.PFE.mySchool.application.query.Scheduel.GetAllScheduelQuery;
import pl.PFE.mySchool.application.query.Scheduel.GetOneActiveClassScheduelQuery;
import pl.PFE.mySchool.application.query.Scheduel.GetOneActiveTeacherScheduelQuery;
import pl.PFE.mySchool.domain.model.Scheduel;

public interface ScheduelQueryHandler {
    Page<Scheduel> handle(GetActiveScheduelQuery query);
    Scheduel handle(GetOneActiveClassScheduelQuery query);
    Scheduel handle(GetOneActiveTeacherScheduelQuery query);
    Page<Scheduel> handle(GetAllScheduelQuery query);
}
