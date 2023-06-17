package pl.PFE.mySchool.application.controller.scheduel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.PFE.mySchool.application.command.Scheduel.ArchiveScheduelCommand;
import pl.PFE.mySchool.application.command.Scheduel.CreateScheduelCommand;
import pl.PFE.mySchool.application.command.Scheduel.UpdateScheduelCommand;
import pl.PFE.mySchool.application.handler.Scheduel.ScheduelCommandHandler;

@RestController
@RequestMapping("/scheduel")
@RequiredArgsConstructor
public class ScheduelCommandController {

    private final ScheduelCommandHandler scheduelCommandHandler;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createScheduel(@Valid @RequestBody CreateScheduelCommand command) {
        scheduelCommandHandler.handle(command);
    }


    @PutMapping("/{id}")
    public void updateScheduel(@PathVariable("id") Long id, @Valid @RequestBody UpdateScheduelCommand command) {
        command.setId(id);
        scheduelCommandHandler.handle(command);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void archiveById(@PathVariable("id") Long id) {
        scheduelCommandHandler.handle(new ArchiveScheduelCommand(id));
    }
}
