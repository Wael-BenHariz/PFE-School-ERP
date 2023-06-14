package pl.PFE.mySchool.domain.service.realisation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.PFE.mySchool.application.command.realisation.ArchiveRealisationCommand;
import pl.PFE.mySchool.application.command.realisation.CreateRealisationCommand;
import pl.PFE.mySchool.application.command.realisation.UpdateRealisationCommand;
import pl.PFE.mySchool.domain.exception.realisation.RealisationAlreadyExistsException;
import pl.PFE.mySchool.domain.exception.realisation.RealisationNotFoundException;
import pl.PFE.mySchool.domain.exception.realisation.RealisationUpdateException;
import pl.PFE.mySchool.domain.model.Realisation;
import pl.PFE.mySchool.domain.model.SchoolClass;
import pl.PFE.mySchool.domain.model.User;
import pl.PFE.mySchool.infrastructure.repository.GradeRepository;
import pl.PFE.mySchool.infrastructure.repository.RealisationRepository;
import pl.PFE.mySchool.infrastructure.repository.SchoolClassRepository;
import pl.PFE.mySchool.domain.model.Subject;
import pl.PFE.mySchool.infrastructure.repository.SubjectRepository;
import pl.PFE.mySchool.infrastructure.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class RealisationCommandService {

    private final RealisationRepository realisationRepository;
    private final SubjectRepository subjectRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final UserRepository userRepository;
    private final GradeRepository gradeRepository;


    public void create(CreateRealisationCommand command) {
        if (realisationRepository.existsByArchivedAndSchoolClassIdAndSubjectId(
                false, command.getClassId(), command.getSubjectId())) {
            throw new RealisationAlreadyExistsException();
        }

        Realisation realisation = new Realisation();
        realisation.setYear(command.getYear());

        Subject subject = subjectRepository.findById(command.getSubjectId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        SchoolClass schoolClass = schoolClassRepository.findById(command.getClassId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        User teacher = userRepository.findById(command.getTeacherId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));


        realisation.setSubject(subject);
        realisation.setSchoolClass(schoolClass);
        realisation.setTeacher(teacher);

        realisationRepository.save(realisation);
    }

    public void update(UpdateRealisationCommand command) {
        Realisation realisation = realisationRepository.findById(command.getId())
                .orElseThrow(RealisationNotFoundException::new);

        if (command.getTeacherId() != null) {
            User teacher = userRepository.findById(command.getTeacherId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            realisation.setTeacher(teacher);
        }
        realisation.setYear(command.getYear() == null ? realisation.getYear() : command.getYear());

        try {
            realisationRepository.save(realisation);
        } catch (Exception e) {
            throw new RealisationUpdateException();
        }
    }

    public void archiveById(ArchiveRealisationCommand command) {
        Realisation realisation = realisationRepository.findById(command.id())
                .orElseThrow(RealisationNotFoundException::new);

        if (realisation.isArchived()) {
            return;
        }

        realisation.getActivities().forEach(activity -> {
            gradeRepository.findByActivityIdAndArchived(activity.getId(), false)
                    .forEach(grade -> grade.setArchived(true));
            activity.setArchived(true);
        });
        realisation.getPosts()
                .forEach(post -> post.setArchived(true));

        realisation.setArchived(true);
        realisationRepository.save(realisation);
    }
}
