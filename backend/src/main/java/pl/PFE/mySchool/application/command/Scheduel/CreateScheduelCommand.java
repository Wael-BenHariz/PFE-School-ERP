package pl.PFE.mySchool.application.command.Scheduel;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateScheduelCommand {

    @NotNull
    @Length(max = 40)
    private String title;

    @NotNull
    @Length(max = 300)
    private String description;

    @NotNull
    private String file_url;

    private Integer teacher_id;

    private Integer class_id;
}
