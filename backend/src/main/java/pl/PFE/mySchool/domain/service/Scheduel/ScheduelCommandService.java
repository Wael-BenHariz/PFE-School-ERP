package pl.PFE.mySchool.domain.service.Scheduel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.PFE.mySchool.application.command.Scheduel.ArchiveScheduelCommand;
import pl.PFE.mySchool.application.command.Scheduel.CreateScheduelCommand;
import pl.PFE.mySchool.application.command.Scheduel.UpdateScheduelCommand;
import pl.PFE.mySchool.domain.exception.activity.ActivityNotFoundException;
import pl.PFE.mySchool.domain.model.Scheduel;
import pl.PFE.mySchool.infrastructure.repository.ScheduelRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduelCommandService {

    private final ScheduelRepository scheduelRepository;

    public Scheduel create(CreateScheduelCommand command) {
        List<Scheduel> scheduelarchive;
        if(command.getClass_id()!=null){
            scheduelarchive=scheduelRepository.findAllByArchivedAndClassId(false,command.getClass_id());
            for (Scheduel scheduel:scheduelarchive) {
                scheduel.setArchived(true);
                scheduelRepository.save(scheduel);
            }
        }
        if(command.getTeacher_id()!=null){
            scheduelarchive=scheduelRepository.findAllByArchivedAndTeacherId(false,command.getTeacher_id());
            for (Scheduel scheduel:scheduelarchive) {
                scheduel.setArchived(true);
                scheduelRepository.save(scheduel);
            }
        }
        Scheduel scheduel = new Scheduel();
        scheduel.setTitle(command.getTitle());
        scheduel.setDescription(command.getDescription());
        scheduel.setFile_url(command.getFile_url());
        scheduel.setClassId(command.getClass_id());
        scheduel.setTeacherId(command.getTeacher_id());
        return scheduelRepository.save(scheduel);
    }


    public Scheduel update(UpdateScheduelCommand command) {
        Scheduel scheduel = scheduelRepository.findById(command.getId())
                .orElseThrow(ActivityNotFoundException::new);

        scheduel.setTitle(command.getTitle() == null ? scheduel.getTitle() : command.getTitle());
        scheduel.setDescription(command.getDescription() == null ? scheduel.getDescription() : command.getDescription());
        scheduel.setFile_url(command.getFile_url() == null ? scheduel.getFile_url() : command.getFile_url());


        if(scheduel.getTeacherId()!=null){
            scheduel.setTeacherId(command.getTeacher_id() == null ? scheduel.getTeacherId() : command.getTeacher_id());
        }else if(scheduel.getClassId()!=null){
            scheduel.setClassId(command.getClass_id() == null ? scheduel.getClassId() : command.getClass_id());
        }

        return scheduelRepository.save(scheduel);
    }

    public void archiveById(ArchiveScheduelCommand command) {
        Scheduel activity = scheduelRepository.findById(command.id())
                .orElseThrow(ActivityNotFoundException::new);
        activity.setArchived(true);
        scheduelRepository.save(activity);
    }
}
