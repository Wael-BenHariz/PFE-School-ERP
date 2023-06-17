package pl.PFE.mySchool.domain.service.Scheduel;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.PFE.mySchool.domain.model.Scheduel;
import pl.PFE.mySchool.infrastructure.repository.ScheduelRepository;

@Service
@RequiredArgsConstructor
public class ScheduelQueryService {
    private final ScheduelRepository scheduelRepository;

    public Page<Scheduel> getAllActiveScheduel( Pageable pageable) {
        return scheduelRepository.findByArchived(false,pageable);
    }

    public Page<Scheduel> getAllScheduel( Pageable pageable) {
        return scheduelRepository.findAll(pageable);
    }
    public Scheduel getTeacherScheduel(Long teacher_id) {
        return scheduelRepository.findByArchivedAndTeacherId(false,teacher_id);
    }

    public Scheduel getClassScheduel(Long class_id) {
        return scheduelRepository.findByArchivedAndClassId(false,class_id);
    }
}
