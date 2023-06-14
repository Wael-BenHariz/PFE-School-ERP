package pl.PFE.mySchool.domain.service.level;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.PFE.mySchool.domain.exception.level.LevelNotFoundException;
import pl.PFE.mySchool.domain.model.Level;
import pl.PFE.mySchool.infrastructure.repository.LevelRepository;
import pl.PFE.mySchool.infrastructure.repository.SchoolClassRepository;

@Service
@RequiredArgsConstructor
public class LevelQueryService {

    private final LevelRepository levelRepository;
    private final SchoolClassRepository schoolClassRepository;

    public Page<Level> getAllActive(Pageable pageable) {
        Page<Level> levels = levelRepository.findByArchived(false, pageable);
        levels.forEach((level -> {
            level.setActiveSchoolClasses(schoolClassRepository.countByArchivedAndLevelId(false, level.getId()));
        }));
        return levels;
    }


    public Page<Level> getAllArchived(Pageable pageable) {
        return levelRepository.findByArchived(true, pageable);
    }


    public Level getById(Long id) {
        return levelRepository.findById(id)
                .orElseThrow(LevelNotFoundException::new);
    }

    public Level getByValue(int level) {
        return levelRepository.findByValue(level)
                .orElseThrow(LevelNotFoundException::new);
    }
}
