package pl.PFE.mySchool.application.command.document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateDocumentCommand {

    private Long id;
    private String title;

    private String description;

    private String file_url;

    private boolean archived;
}
