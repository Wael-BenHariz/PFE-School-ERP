package pl.PFE.mySchool.domain.shared;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.infrastructure.repository.ActivityRepository;
import pl.PFE.mySchool.infrastructure.repository.GradeRepository;
import pl.PFE.mySchool.infrastructure.repository.PostRepository;
import pl.PFE.mySchool.infrastructure.repository.RealisationRepository;
import pl.PFE.mySchool.domain.exception.activity.ActivityNotFoundException;
import pl.PFE.mySchool.domain.exception.grade.GradeNotFoundException;
import pl.PFE.mySchool.domain.exception.post.PostNotFoundException;
import pl.PFE.mySchool.domain.exception.realisation.NotAffiliatedWithRealisationException;
import pl.PFE.mySchool.domain.exception.realisation.RealisationArchivedException;
import pl.PFE.mySchool.domain.exception.realisation.RealisationNotFoundException;
import pl.PFE.mySchool.domain.model.Activity;
import pl.PFE.mySchool.domain.model.Grade;
import pl.PFE.mySchool.domain.model.Post;
import pl.PFE.mySchool.domain.model.Realisation;
import pl.PFE.mySchool.domain.model.Role;
import pl.PFE.mySchool.domain.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AccessGuard {

    private final RealisationRepository realisationRepository;
    private final ActivityRepository activityRepository;
    private final PostRepository postRepository;
    private final GradeRepository gradeRepository;

    public void checkAccessToRealisation(Long realisationId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            throw new NotAffiliatedWithRealisationException();
        }
        List<Role> privilegedRoles = new ArrayList<>(Arrays.asList(Role.OFFICE, Role.DIRECTOR, Role.ADMIN));

        Realisation realisation = realisationRepository.findById(realisationId)
                .orElseThrow(RealisationNotFoundException::new);

        if (realisation.isArchived() && !privilegedRoles.contains(user.getRole())) {
            throw new RealisationArchivedException();
        }

        boolean isAffiliatedAsStudent = realisation.getSchoolClass()
                .getStudents()
                .stream()
                .anyMatch((student -> Objects.equals(student.getId(), user.getId())));

        boolean isAffiliatedAsTeacher = Objects.equals(realisation.getTeacher().getId(), user.getId());

        if (!isAffiliatedAsStudent && !isAffiliatedAsTeacher && !privilegedRoles.contains(user.getRole())) {
            throw new NotAffiliatedWithRealisationException();
        }
    }

    public void checkAccessToActivity(Long activityId) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(ActivityNotFoundException::new);
        checkAccessToRealisation(activity.getRealisation().getId());
    }

    public void checkAccessToPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        checkAccessToRealisation(post.getRealisation().getId());
    }

    public void checkAccessToGrade(Long gradeId) {
        Grade grade = gradeRepository.findById(gradeId)
                .orElseThrow(GradeNotFoundException::new);
        checkAccessToRealisation(grade.getActivity().getRealisation().getId());
    }
}
