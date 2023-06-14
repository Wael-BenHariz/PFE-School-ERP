package pl.PFE.mySchool.domain.service.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.PFE.mySchool.application.command.subject.ArchiveSubjectCommand;
import pl.PFE.mySchool.application.command.subject.CreateSubjectCommand;
import pl.PFE.mySchool.application.command.subject.UpdateSubjectCommand;
import pl.PFE.mySchool.application.command.subject.UpdateSubjectImageCommand;
import pl.PFE.mySchool.domain.exception.subject.SubjectNotFoundException;
import pl.PFE.mySchool.domain.exception.subject.SubjectUpdateException;
import pl.PFE.mySchool.domain.model.Grade;
import pl.PFE.mySchool.domain.model.Realisation;
import pl.PFE.mySchool.domain.shared.ImageService;
import pl.PFE.mySchool.infrastructure.repository.GradeRepository;
import pl.PFE.mySchool.infrastructure.repository.RealisationRepository;
import pl.PFE.mySchool.domain.model.Subject;
import pl.PFE.mySchool.infrastructure.repository.SubjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectCommandService {

    private final SubjectRepository subjectRepository;
    private final RealisationRepository realisationRepository;
    private final GradeRepository gradeRepository;
    private final ImageService imageService;


    public void create(CreateSubjectCommand command) {
        Subject subject = new Subject();
        subject.setName(command.getName());
        subject.setAbbreviation(command.getAbbreviation());
        subject.setImageUrl("https://media.istockphoto.com/id/1335708681/photo/stacks-of-books-for-teaching-knowledge-college-library-green-background.jpg?b=1&s=170667a&w=0&k=20&c=_-kpFpVyUWgiCafriJgZ6Wr_kLQ2kt19SSCZvlWK63Y=");

        subjectRepository.save(subject);
    }


    public void archiveById(ArchiveSubjectCommand command) {
        Subject subject = subjectRepository.findById(command.id())
                .orElseThrow(SubjectNotFoundException::new);

        if (subject.isArchived()) {
            return;
        }

        List<Realisation> realisations = realisationRepository.findByArchivedAndSubjectId(false, subject.getId());
        realisations.forEach(realisation -> {
            realisation.getPosts().forEach(post -> post.setArchived(true));
            realisation.getActivities().forEach(activity -> {
                List<Grade> grades = gradeRepository.findByActivityIdAndArchived(activity.getId(), false);
                grades.forEach((grade -> grade.setArchived(true)));
                activity.setArchived(true);
            });
            realisation.setArchived(true);
        });

        subject.setArchived(true);
        subjectRepository.save(subject);
    }


    public void update(UpdateSubjectCommand command) {
        Subject subject = subjectRepository.findById(command.getId())
                .orElseThrow(SubjectNotFoundException::new);

        subject.setName(command.getName() == null ? subject.getName() : command.getName());
        subject.setAbbreviation(command.getAbbreviation() == null ? subject.getAbbreviation() : command.getAbbreviation());

        try {
            subjectRepository.save(subject);
        } catch (Exception e) {
            throw new SubjectUpdateException();
        }
    }


    public void updateSubjectImage(UpdateSubjectImageCommand command) {
        Subject subject = subjectRepository.findById(command.getId())
                .orElseThrow(SubjectNotFoundException::new);

        try {
            String imageUrl = imageService.saveImage(command.getImage());
            subject.setImageUrl(imageUrl);
            subjectRepository.save(subject);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
