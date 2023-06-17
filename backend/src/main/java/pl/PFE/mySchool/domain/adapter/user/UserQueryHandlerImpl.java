package pl.PFE.mySchool.domain.adapter.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.handler.user.UserQueryHandler;
import pl.PFE.mySchool.application.query.user.*;
import pl.PFE.mySchool.domain.model.User;
import pl.PFE.mySchool.domain.service.user.UserQueryService;
import pl.PFE.mySchool.application.dto.TokenDTO;
import pl.PFE.mySchool.domain.model.Role;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserQueryHandlerImpl implements UserQueryHandler {

    private final UserQueryService userQueryService;


    @Override
    public User handle(GetUserByIdQuery query) {
        return userQueryService.getById(query.id());
    }

    @Override
    public Page<User> handle(GetActiveUsersQuery query) {
        return userQueryService.getAllActiveUsers(query.pageable());
    }

    @Override
    public Page<User> handle(GetArchivedUsersQuery query) {
        return userQueryService.getAllArchivedUsers(query.pageable());
    }

    @Override
    public Page<User> handle(GetUserByKeywordQuery query) {
        return userQueryService.getByKeywordContaining(query.email(), query.pageable());
    }

    @Override
    public Page<User> handle(GetActiveStudentsQuery query) {
        return userQueryService.getAllActiveStudents(query.pageable());
    }

    @Override
    public Page<User> handle(GetActiveTeachersQuery query) {
        return userQueryService.getAllActiveTeachers(query.pageable());
    }

    @Override
    public List<User> handle(GetAllTeachersQuery query) {
        return userQueryService.getAllTeachers(query.pageable());
    }

    @Override
    public Page<User> handle(GetActiveOfficesQuery query) {
        return userQueryService.getAllActiveOffices(query.pageable());
    }

    @Override
    public Page<User> handle(GetActiveDirectorsQuery query) {
        return userQueryService.getAllActiveDirectors(query.pageable());
    }

    @Override
    public Page<User> handle(GetArchivedStudentsQuery query) {
        return userQueryService.getAllArchiveStudents(query.pageable());
    }

    @Override
    public Page<User> handle(GetArchivedTeachersQuery query) {
        return userQueryService.getAllArchiveTeachers(query.pageable());
    }

    @Override
    public Page<User> handle(GetArchivedOfficesQuery query) {
        return userQueryService.getAllArchiveOffices(query.pageable());
    }

    @Override
    public Page<User> handle(GetArchivedDirectorsQuery query) {
        return userQueryService.getAllArchiveDirectors(query.pageable());
    }

    @Override
    public User handle(GetLoggedInUserQuery getLoggedInUserQuery) {
        return userQueryService.getLoggedInUser();
    }

    @Override
    public List<User> handle(GetNotSupervisingActiveTeachersQuery query) {
        return userQueryService.getNotSupervisingActiveTeachers(query.pageable());
    }

    @Override
    public Page<TokenDTO> handle(GetStudentTokensQuery query) {
        return userQueryService.getTokensByRole(Role.STUDENT, query.pageable());
    }

    @Override
    public Page<TokenDTO> handle(GetTeacherTokensQuery query) {
        return userQueryService.getTokensByRole(Role.TEACHER, query.pageable());
    }

    @Override
    public Page<TokenDTO> handle(GetOfficeTokensQuery query) {
        return userQueryService.getTokensByRole(Role.OFFICE, query.pageable());
    }

    @Override
    public Page<TokenDTO> handle(GetDirectorTokensQuery query) {
        return userQueryService.getTokensByRole(Role.DIRECTOR, query.pageable());
    }

    @Override
    public Page<User> handle(GetUnassignedStudentsQuery query) {
        return userQueryService.getUnassignedStudents(query.pageable());
    }
}
