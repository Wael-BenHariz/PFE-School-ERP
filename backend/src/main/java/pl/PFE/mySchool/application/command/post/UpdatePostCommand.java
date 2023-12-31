package pl.PFE.mySchool.application.command.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatePostCommand {

    private Long id;

    private String title;

    private String content;

    private String file_url;
}
