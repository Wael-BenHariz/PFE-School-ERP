package pl.PFE.mySchool.application.command.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateDocumentCommand {

    private String title;

    private String description;

    private String file_url;

    private boolean archived;
}
