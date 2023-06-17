package pl.PFE.mySchool.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.PFE.mySchool.domain.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {


}
