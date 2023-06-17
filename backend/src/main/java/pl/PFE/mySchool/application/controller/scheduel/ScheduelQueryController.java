package pl.PFE.mySchool.application.controller.scheduel;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.PFE.mySchool.application.handler.Scheduel.ScheduelQueryHandler;
import pl.PFE.mySchool.application.query.Scheduel.GetActiveScheduelQuery;
import pl.PFE.mySchool.application.query.Scheduel.GetAllScheduelQuery;
import pl.PFE.mySchool.application.query.Scheduel.GetOneActiveClassScheduelQuery;
import pl.PFE.mySchool.application.query.Scheduel.GetOneActiveTeacherScheduelQuery;
import pl.PFE.mySchool.domain.model.Scheduel;

@RestController
@RequestMapping("/scheduel")
@RequiredArgsConstructor
public class ScheduelQueryController {

    private final ScheduelQueryHandler scheduelQueryHandler;

    @GetMapping("/TeacherScheduel/{id}")
    public Scheduel getTeacherScheduel(@PathVariable("id") Long id) {
        return scheduelQueryHandler.handle(new GetOneActiveTeacherScheduelQuery(id));
    }

    @GetMapping("/ClassScheduel/{id}")
    public Scheduel getClassScheduel(@PathVariable("id") Long id) {
        return scheduelQueryHandler.handle(new GetOneActiveClassScheduelQuery(id));
    }

    @GetMapping("/all")
    public Page<Scheduel> getAllSchediel(Pageable pageable) {
        return scheduelQueryHandler.handle(new GetAllScheduelQuery(pageable));
    }

    @GetMapping("/allActive")
    public Page<Scheduel> getAllActiveSchediel(Pageable pageable) {
        return scheduelQueryHandler.handle(new GetActiveScheduelQuery(pageable));
    }

}
