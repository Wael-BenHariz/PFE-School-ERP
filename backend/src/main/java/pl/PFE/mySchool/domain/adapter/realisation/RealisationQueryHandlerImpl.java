package pl.PFE.mySchool.domain.adapter.realisation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.handler.realisation.RealisationQueryHandler;
import pl.PFE.mySchool.domain.model.Realisation;
import pl.PFE.mySchool.domain.service.realisation.RealisationQueryService;
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

@RequiredArgsConstructor
@Component
public class RealisationQueryHandlerImpl implements RealisationQueryHandler {

    private final RealisationQueryService realisationQueryService;

    @Override
    public Page<RealisationDTO> handle(GetActiveRealisationsQuery query) {
        return realisationQueryService.getAllActive(query.pageable());
    }

    @Override
    public Page<RealisationDTO> handle(GetArchivedRealisationsQuery query) {
        return realisationQueryService.getAllArchived(query.pageable());
    }

    @Override
    public Realisation handle(GetRealisationByIdQuery query) {
        return realisationQueryService.getById(query.id());
    }

    @Override
    public AverageGradeDTO handle(GetRealisationAverageGradeQuery query) {
        return realisationQueryService.getRealisationAverageGrade(query.id());
    }

    @Override
    public List<SubjectDTO> handle(GetOwnRealisationsQuery query) {
        return realisationQueryService.getOwnActiveRealisations();
    }

    @Override
    public RealisationDTO handle(GetRealisationInfoByIdQuery query) {
        return realisationQueryService.getInfoById(query.id());
    }
}
