package pl.PFE.mySchool.domain.service.Scheduel;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.PFE.mySchool.domain.model.Scheduel;
import pl.PFE.mySchool.domain.model.User;
import pl.PFE.mySchool.infrastructure.repository.ScheduelRepository;
import pl.PFE.mySchool.infrastructure.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ScheduelQueryService {
    private final ScheduelRepository scheduelRepository;
    private final UserRepository userRepository;

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
        User etud =userRepository.findById(class_id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        return scheduelRepository.findByArchivedAndClassId(false,etud.getSchoolClass().getId());
    }
}
