package pl.PFE.mySchool.application.handler.user;

import org.springframework.data.domain.Page;
import pl.PFE.mySchool.domain.model.User;
import pl.PFE.mySchool.application.dto.TokenDTO;
import pl.PFE.mySchool.application.query.user.GetActiveDirectorsQuery;
import pl.PFE.mySchool.application.query.user.GetActiveOfficesQuery;
import pl.PFE.mySchool.application.query.user.GetActiveStudentsQuery;
import pl.PFE.mySchool.application.query.user.GetActiveTeachersQuery;
import pl.PFE.mySchool.application.query.user.GetActiveUsersQuery;
import pl.PFE.mySchool.application.query.user.GetArchivedDirectorsQuery;
import pl.PFE.mySchool.application.query.user.GetArchivedOfficesQuery;
import pl.PFE.mySchool.application.query.user.GetArchivedStudentsQuery;
import pl.PFE.mySchool.application.query.user.GetArchivedTeachersQuery;
import pl.PFE.mySchool.application.query.user.GetArchivedUsersQuery;
import pl.PFE.mySchool.application.query.user.GetDirectorTokensQuery;
import pl.PFE.mySchool.application.query.user.GetLoggedInUserQuery;
import pl.PFE.mySchool.application.query.user.GetNotSupervisingActiveTeachersQuery;
import pl.PFE.mySchool.application.query.user.GetOfficeTokensQuery;
import pl.PFE.mySchool.application.query.user.GetStudentTokensQuery;
import pl.PFE.mySchool.application.query.user.GetTeacherTokensQuery;
import pl.PFE.mySchool.application.query.user.GetUnassignedStudentsQuery;
import pl.PFE.mySchool.application.query.user.GetUserByIdQuery;
import pl.PFE.mySchool.application.query.user.GetUserByKeywordQuery;

import java.util.List;

public interface UserQueryHandler {

    User handle(GetUserByIdQuery query);

    Page<User> handle(GetActiveUsersQuery getActiveUsersQuery);

    Page<User> handle(GetArchivedUsersQuery getArchivedUsersQuery);

    Page<User> handle(GetUserByKeywordQuery query);

    Page<User> handle(GetActiveStudentsQuery query);

    Page<User> handle(GetActiveTeachersQuery query);

    Page<User> handle(GetActiveOfficesQuery query);

    Page<User> handle(GetActiveDirectorsQuery query);

    Page<User> handle(GetArchivedStudentsQuery query);

    Page<User> handle(GetArchivedTeachersQuery query);

    Page<User> handle(GetArchivedOfficesQuery query);

    Page<User> handle(GetArchivedDirectorsQuery query);

    User handle(GetLoggedInUserQuery query);

    List<User> handle(GetNotSupervisingActiveTeachersQuery query);

    Page<TokenDTO> handle(GetStudentTokensQuery query);

    Page<TokenDTO> handle(GetTeacherTokensQuery query);

    Page<TokenDTO> handle(GetOfficeTokensQuery query);

    Page<TokenDTO> handle(GetDirectorTokensQuery query);

    Page<User> handle(GetUnassignedStudentsQuery query);
}
