package pl.PFE.mySchool.domain.service.grade;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.PFE.mySchool.domain.exception.grade.GradeNotFoundException;
import pl.PFE.mySchool.domain.model.Activity;
import pl.PFE.mySchool.domain.model.Grade;
import pl.PFE.mySchool.domain.model.User;
import pl.PFE.mySchool.infrastructure.repository.ActivityRepository;
import pl.PFE.mySchool.infrastructure.repository.GradeRepository;
import pl.PFE.mySchool.application.dto.GradeDTO;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class GradeQueryService {

    private final GradeRepository gradeRepository;
    private final ActivityRepository activityRepository;

    public Page<Grade> getAllActiveByStudent(Pageable pageable) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return gradeRepository.findAllByArchivedAndStudentId(false, user.getId(), pageable);
    }

    public Page<Grade> getAllArchivedByStudent(Long studentId, Pageable pageable) {
        return gradeRepository.findAllByArchivedAndStudentId(true, studentId, pageable);
    }

    public Grade getByActivityAndStudent(Long activityId, Long studentId) {
        return gradeRepository.findByActivityIdAndStudentId(activityId, studentId)
                .orElseThrow(GradeNotFoundException::new);
    }

    public Grade getById(Long id) {
        return gradeRepository.findById(id)
                .orElseThrow(GradeNotFoundException::new);
    }

    public Page<GradeDTO> getOwnGradesByRealisation(Long realisationId, Pageable pageable) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Page<Activity> activities = activityRepository
                .findByRealisationIdAndArchived(realisationId, false, pageable);
        List<Grade> grades = gradeRepository
                .findAllByArchivedAndStudentIdAndActivityRealisationId(false, user.getId(), realisationId);

        return activities.map((activity -> {
            AtomicReference<Grade> gradeRef = new AtomicReference<>();
            grades.stream()
                    .anyMatch((grade) -> {
                        if (Objects.equals(grade.getActivity().getId(), activity.getId())) {
                            gradeRef.set(grade);
                            return true;
                        }
                        return false;
                    });
            return new GradeDTO(activity, gradeRef.get());
        }));
    }
}
