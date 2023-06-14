package pl.PFE.mySchool.domain.exception.subject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.PFE.mySchool.domain.exception.MySchoolException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SubjectNotFoundException extends MySchoolException {
}
