package pl.PFE.mySchool.application.command.Scheduel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateScheduelCommand {

    private Long id;

    @Length(max = 40)
    private String title;

    @Length(max = 300)
    private String description;

    private String file_url;

    private Integer teacher_id;

    private Integer class_id;
}
