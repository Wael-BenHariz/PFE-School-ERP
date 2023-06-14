package pl.PFE.mySchool.domain.service.grade;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.PFE.mySchool.application.command.grade.ArchiveGradeCommand;
import pl.PFE.mySchool.application.command.grade.CreateGradeCommand;
import pl.PFE.mySchool.application.command.grade.UpdateGradeCommand;
import pl.PFE.mySchool.domain.exception.grade.GradeNotFoundException;
import pl.PFE.mySchool.domain.model.Activity;
import pl.PFE.mySchool.domain.model.Grade;
import pl.PFE.mySchool.domain.model.User;
import pl.PFE.mySchool.domain.shared.AccessGuard;
import pl.PFE.mySchool.infrastructure.repository.ActivityRepository;
import pl.PFE.mySchool.infrastructure.repository.GradeRepository;
import pl.PFE.mySchool.infrastructure.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GradeCommandService {

    private final GradeRepository gradeRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final AccessGuard accessGuard;


    public Grade create(CreateGradeCommand command) {
        Optional<Grade> optionalGrade = gradeRepository.findByActivityIdAndStudentId(command.getActivityId(), command.getStudentId());
        Grade grade = optionalGrade.orElseGet(Grade::new);

        grade.setValue(command.getValue());
        grade.setComment(command.getComment());

        Activity activity = activityRepository.findById(command.getActivityId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        User student = userRepository.findById(command.getStudentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        User teacher = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        grade.setActivity(activity);
        grade.setStudent(student);
        grade.setTeacher(teacher);

        return gradeRepository.save(grade);
    }

    public Grade update(UpdateGradeCommand command) {
        Grade grade = gradeRepository.findById(command.getGradeId())
                .orElseThrow(GradeNotFoundException::new);

        grade.setValue(command.getValue() == null ? grade.getValue() : command.getValue());
        grade.setEdited(true);

        return gradeRepository.save(grade);
    }

    public void archiveById(ArchiveGradeCommand command) {
        Grade grade = gradeRepository.findById(command.id())
                .orElseThrow(GradeNotFoundException::new);

        accessGuard.checkAccessToActivity(grade.getActivity().getId());

        grade.setArchived(true);
        gradeRepository.save(grade);
    }
}
