package pl.PFE.mySchool.application.handler.realisation;

import org.springframework.data.domain.Page;
import pl.PFE.mySchool.domain.model.Realisation;
import pl.PFE.mySchool.application.dto.AverageGradeDTO;
import pl.PFE.mySchool.application.dto.RealisationDTO;
import pl.PFE.mySchool.application.dto.SubjectDTO;
import pl.PFE.mySchool.application.query.realisation.GetActiveRealisationsQuery;
import pl.PFE.mySchool.application.query.realisation.GetArchivedRealisationsQuery;
import pl.PFE.mySchool.application.query.realisation.GetOwnRealisationsQuery;
import pl.PFE.mySchool.application.query.realisation.GetRealisationAverageGradeQuery;
import pl.PFE.mySchool.application.query.realisation.GetRealisationByIdQuery;
import pl.PFE.mySchool.application.query.realisation.GetRealisationInfoByIdQuery;

import java.util.List;

public interface RealisationQueryHandler {
    Page<RealisationDTO> handle(GetActiveRealisationsQuery query);

    Page<RealisationDTO> handle(GetArchivedRealisationsQuery query);

    Realisation handle(GetRealisationByIdQuery query);

    AverageGradeDTO handle(GetRealisationAverageGradeQuery getRealisationAverageGradeQuery);

    List<SubjectDTO> handle(GetOwnRealisationsQuery getOwnRealisationsQuery);

    RealisationDTO handle(GetRealisationInfoByIdQuery getRealisationInfoByIdQuery);
}
