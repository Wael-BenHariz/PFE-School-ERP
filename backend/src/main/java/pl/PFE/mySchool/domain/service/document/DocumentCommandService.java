package pl.PFE.mySchool.domain.service.document;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.PFE.mySchool.application.command.document.ArchiveDocumentCommand;
import pl.PFE.mySchool.application.command.document.CreateDocumentCommand;
import pl.PFE.mySchool.application.command.document.UpdateDocumentCommand;
import pl.PFE.mySchool.application.command.level.ArchiveLevelCommand;
import pl.PFE.mySchool.application.command.level.UpdateLevelCommand;
import pl.PFE.mySchool.domain.exception.level.ActiveSchoolClassesException;
import pl.PFE.mySchool.domain.exception.level.LevelAlreadyExistsException;
import pl.PFE.mySchool.domain.exception.level.LevelNotFoundException;
import pl.PFE.mySchool.domain.model.Document;
import pl.PFE.mySchool.domain.model.Level;
import pl.PFE.mySchool.infrastructure.repository.DocumentRepository;

import javax.print.Doc;
import java.util.Objects;


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

    public void update(UpdateDocumentCommand command) {
        Document document = documentRepository.findById(command.getId())
                .orElseThrow(LevelNotFoundException::new);
        document.setTitle(command.getTitle() == null ? document.getTitle() : command.getTitle());
        document.setDescription(command.getDescription() == null ? document.getDescription() : command.getDescription());
        document.setFile_url(command.getFile_url()== null ? document.getFile_url() : command.getFile_url());
        documentRepository.save(document);
    }

    public void archiveById(ArchiveDocumentCommand command) {
        Document document = documentRepository.findById(command.id())
                .orElseThrow(LevelNotFoundException::new);
        document.setArchived(true);
        documentRepository.save(document);
    }
}
