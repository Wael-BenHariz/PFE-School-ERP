package pl.PFE.mySchool.domain.service.schoolclass;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.PFE.mySchool.domain.exception.schoolclass.SchoolClassNotFoundException;
import pl.PFE.mySchool.domain.model.SchoolClass;
import pl.PFE.mySchool.infrastructure.repository.SchoolClassRepository;

@Service
@RequiredArgsConstructor
public class SchoolClassQueryService {

    private final SchoolClassRepository schoolClassRepository;

    public Page<SchoolClass> getAllActive(Pageable pageable) {
        return schoolClassRepository.findAllByArchived(false, pageable);
    }


    public Page<SchoolClass> getAllArchived(Pageable pageable) {
        return schoolClassRepository.findAllByArchived(true, pageable);
    }


    public SchoolClass getById(Long id) {
        return schoolClassRepository.findById(id)
                .orElseThrow(SchoolClassNotFoundException::new);
    }
}
