package pl.PFE.mySchool.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    private Long realisationId;

    private String documentName;

    private String description;

    private String FileUrl;


}
