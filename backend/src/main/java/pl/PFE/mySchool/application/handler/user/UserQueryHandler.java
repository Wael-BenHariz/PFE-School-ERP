package pl.PFE.mySchool.application.handler.user;

import org.springframework.data.domain.Page;
import pl.PFE.mySchool.application.query.user.*;
import pl.PFE.mySchool.domain.model.User;
import pl.PFE.mySchool.application.dto.TokenDTO;

import java.util.List;

public interface UserQueryHandler {

    User handle(GetUserByIdQuery query);

    Page<User> handle(GetActiveUsersQuery getActiveUsersQuery);

    Page<User> handle(GetArchivedUsersQuery getArchivedUsersQuery);

    Page<User> handle(GetUserByKeywordQuery query);

    Page<User> handle(GetActiveStudentsQuery query);

    Page<User> handle(GetActiveTeachersQuery query);

    List<User>  handle(GetAllTeachersQuery query);

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
