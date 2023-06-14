package pl.PFE.mySchool.domain.exception.activity;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.PFE.mySchool.domain.exception.MySchoolException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ActivityUpdateException extends MySchoolException {
}
