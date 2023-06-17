package pl.PFE.mySchool.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Scheduel")
public class Scheduel extends AbstractEntity {

    private String title;

    private String description;

    private String file_url;

    private Integer teacherId;

    private Integer classId;

    private boolean archived;
}
