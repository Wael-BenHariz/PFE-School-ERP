package pl.PFE.mySchool.domain.service.document;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.PFE.mySchool.application.command.document.CreateDocumentCommand;
import pl.PFE.mySchool.domain.exception.level.LevelAlreadyExistsException;
import pl.PFE.mySchool.domain.model.Document;
import pl.PFE.mySchool.domain.model.Level;
import pl.PFE.mySchool.infrastructure.repository.DocumentRepository;


@Service
@RequiredArgsConstructor
@Transactional
public class DocumentCommandService {

    private final DocumentRepository documentRepository;

    public Document create(CreateDocumentCommand command) {
        Document document = new Document();
        document.setTitle(command.getTitle());
        document.setDescription(command.getDescription());
        document.setFile_url(command.getFile_url());

        return documentRepository.save(document);
    }
}
