package pl.PFE.mySchool.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.PFE.mySchool.domain.model.Scheduel;

import java.util.List;

@Repository
public interface ScheduelRepository extends JpaRepository<Scheduel, Long> {

    Page<Scheduel> findByArchived(boolean archived, Pageable pageable);

    Page<Scheduel> findAll(Pageable pageable);

    Scheduel findByArchivedAndTeacherId(boolean archived,Long teacherId);

    Scheduel findByArchivedAndClassId(boolean archived, Long classId);

    List<Scheduel> findAllByArchivedAndClassId(boolean archived, Integer classId);

    List<Scheduel> findAllByArchivedAndTeacherId(boolean archived,Integer teacherId);
}
