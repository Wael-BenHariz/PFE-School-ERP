package pl.PFE.mySchool.application.controller.schoolclass;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.PFE.mySchool.application.command.schoolclass.ArchiveSchoolClassCommand;
import pl.PFE.mySchool.application.command.schoolclass.CreateSchoolClassCommand;
import pl.PFE.mySchool.application.command.schoolclass.UpdateSchoolClassCommand;
import pl.PFE.mySchool.application.handler.schoolclass.SchoolClassCommandHandler;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
public class SchoolClassCommandController {

    private final SchoolClassCommandHandler schoolClassCommandHandler;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public void createSchoolClass(@Valid @RequestBody CreateSchoolClassCommand command) {
        schoolClassCommandHandler.handle(command);
    }

    @PutMapping("/{id}")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public void updateSchoolClass(@PathVariable("id") Long id, @Valid @RequestBody UpdateSchoolClassCommand command) {
        command.setId(id);
        schoolClassCommandHandler.handle(command);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Secured({"DIRECTOR", "ADMIN"})
    public void archiveById(@PathVariable("id") Long id) {
        schoolClassCommandHandler.handle(new ArchiveSchoolClassCommand(id));
    }
}
