package pl.PFE.mySchool.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "DOCUMENT")
@NoArgsConstructor
public class Document extends AbstractEntity  {

    private String title;

    private String description;

    private String file_url;

    private boolean archived;

}
