package pl.PFE.mySchool.domain.adapter.Scheduel;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.handler.Scheduel.ScheduelQueryHandler;
import pl.PFE.mySchool.application.query.Scheduel.GetActiveScheduelQuery;
import pl.PFE.mySchool.application.query.Scheduel.GetAllScheduelQuery;
import pl.PFE.mySchool.application.query.Scheduel.GetOneActiveClassScheduelQuery;
import pl.PFE.mySchool.application.query.Scheduel.GetOneActiveTeacherScheduelQuery;
import pl.PFE.mySchool.domain.model.Scheduel;
import pl.PFE.mySchool.domain.service.Scheduel.ScheduelQueryService;

@RequiredArgsConstructor
@Component
public class ScheduelQueryHandlerImpl implements ScheduelQueryHandler {
    private final ScheduelQueryService service;

    @Override
    public Page<Scheduel> handle(GetActiveScheduelQuery query) {
        return service.getAllActiveScheduel(query.pageable());
    }

    @Override
    public Scheduel handle(GetOneActiveClassScheduelQuery query) {
        return service.getClassScheduel(query.idClass());
    }

    @Override
    public Scheduel handle(GetOneActiveTeacherScheduelQuery query) {
        return service.getTeacherScheduel(query.idTeacher());
    }

    @Override
    public Page<Scheduel> handle(GetAllScheduelQuery query) {
        return service.getAllScheduel(query.pageable());
    }
}
