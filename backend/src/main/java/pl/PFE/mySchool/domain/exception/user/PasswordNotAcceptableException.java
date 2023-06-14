package pl.PFE.mySchool.domain.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.PFE.mySchool.domain.exception.MySchoolException;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class PasswordNotAcceptableException extends MySchoolException {
}
