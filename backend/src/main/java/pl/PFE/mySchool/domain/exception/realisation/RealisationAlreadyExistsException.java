package pl.PFE.mySchool.domain.exception.realisation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.PFE.mySchool.domain.exception.MySchoolException;

@ResponseStatus(HttpStatus.CONFLICT)
public class RealisationAlreadyExistsException extends MySchoolException {
}
