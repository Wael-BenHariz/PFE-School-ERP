package pl.PFE.mySchool.application.handler.level;

import org.springframework.data.domain.Page;
import pl.PFE.mySchool.domain.model.Level;
import pl.PFE.mySchool.application.query.level.GetActiveLevelsQuery;
import pl.PFE.mySchool.application.query.level.GetArchivedLevelsQuery;
import pl.PFE.mySchool.application.query.level.GetLevelByIdQuery;
import pl.PFE.mySchool.application.query.level.GetLevelByValueQuery;

public interface LevelQueryHandler {
    Page<Level> handle(GetActiveLevelsQuery query);

    Page<Level> handle(GetArchivedLevelsQuery query);

    Level handle(GetLevelByIdQuery query);

    Level handle(GetLevelByValueQuery query);
}
