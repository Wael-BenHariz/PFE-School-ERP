package pl.PFE.mySchool.domain.service.document;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.PFE.mySchool.domain.exception.activity.ActivityNotFoundException;
import pl.PFE.mySchool.domain.model.Document;
import pl.PFE.mySchool.infrastructure.repository.DocumentRepository;

@Service
@RequiredArgsConstructor
public class DocumentQueryService {

    private final DocumentRepository documentRepository;

    public Page<Document> getDocuments(Pageable pageable) {
          Page<Document> documents = documentRepository.findAll(pageable);
        return(documents);

    }

    public Document getById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(ActivityNotFoundException::new);
    }
}
