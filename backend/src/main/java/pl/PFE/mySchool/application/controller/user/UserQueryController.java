package pl.PFE.mySchool.application.controller.user;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.PFE.mySchool.application.query.user.*;
import pl.PFE.mySchool.domain.model.User;
import pl.PFE.mySchool.application.dto.TokenDTO;
import pl.PFE.mySchool.application.handler.user.UserQueryHandler;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserQueryController {

    private final UserQueryHandler userQueryHandler;


    @GetMapping("/{id}")
    @Secured({"STUDENT", "TEACHER", "PARENT", "OFFICE", "DIRECTOR", "ADMIN"})
    public User getUserById(@PathVariable("id") Long id) {
        return userQueryHandler.handle(new GetUserByIdQuery(id));
    }

    @GetMapping("/me")
    @Secured({"STUDENT", "TEACHER", "PARENT", "OFFICE", "DIRECTOR", "ADMIN"})
    public User getLoggedInUser() {
        return userQueryHandler.handle(new GetLoggedInUserQuery());
    }

    @GetMapping("/search")
    @Secured({"STUDENT", "TEACHER", "PARENT", "OFFICE", "DIRECTOR", "ADMIN"})
    public Page<User> searchForUser(@PathParam("keyword") String keyword, Pageable pageable) {
        return userQueryHandler.handle(new GetUserByKeywordQuery(keyword, pageable));
    }

    @GetMapping
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Page<User> getAllActiveUsers(Pageable pageable) {
        return userQueryHandler.handle(new GetActiveUsersQuery(pageable));
    }

    @GetMapping("/archived")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Page<User> getAllArchivedUsers(Pageable pageable) {
        return userQueryHandler.handle(new GetArchivedUsersQuery(pageable));
    }

    @GetMapping("/students")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Page<User> getAllActiveStudents(Pageable pageable) {
        return userQueryHandler.handle(new GetActiveStudentsQuery(pageable));
    }

    @GetMapping("/teachers")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Page<User> getAllActiveTeachers(Pageable pageable) {
        return userQueryHandler.handle(new GetActiveTeachersQuery(pageable));
    }

    @GetMapping("/AllTeachers")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public List<User> getAllTeachers(Pageable pageable) {
        return userQueryHandler.handle(new GetAllTeachersQuery(pageable));
    }

    @GetMapping("/teachers/free")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public List<User> getAllNotSupervisingActiveTeachers(Pageable pageable) {
        return userQueryHandler.handle(new GetNotSupervisingActiveTeachersQuery(pageable));
    }

    @GetMapping("/offices")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Page<User> getAllActiveOffices(Pageable pageable) {
        return userQueryHandler.handle(new GetActiveOfficesQuery(pageable));
    }

    @GetMapping("/directors")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Page<User> getAllActiveDirectors(Pageable pageable) {
        return userQueryHandler.handle(new GetActiveDirectorsQuery(pageable));
    }

    @GetMapping("/students/archived")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Page<User> getAllArchivedStudents(Pageable pageable) {
        return userQueryHandler.handle(new GetArchivedStudentsQuery(pageable));
    }

    @GetMapping("/teachers/archived")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Page<User> getAllArchivedTeachers(Pageable pageable) {
        return userQueryHandler.handle(new GetArchivedTeachersQuery(pageable));
    }

    @GetMapping("/offices/archived")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Page<User> getAllArchivedOffices(Pageable pageable) {
        return userQueryHandler.handle(new GetArchivedOfficesQuery(pageable));
    }

    @GetMapping("/directors/archived")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Page<User> getAllArchivedDirectors(Pageable pageable) {
        return userQueryHandler.handle(new GetArchivedDirectorsQuery(pageable));
    }

    @GetMapping("/tokens/students")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Page<TokenDTO> getStudentTokens(Pageable pageable) {
        return userQueryHandler.handle(new GetStudentTokensQuery(pageable));
    }

    @GetMapping("/tokens/teachers")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Page<TokenDTO> getTeacherTokens(Pageable pageable) {
        return userQueryHandler.handle(new GetTeacherTokensQuery(pageable));
    }

    @GetMapping("/tokens/offices")
    @Secured({"DIRECTOR", "ADMIN"})
    public Page<TokenDTO> getOfficeTokens(Pageable pageable) {
        return userQueryHandler.handle(new GetOfficeTokensQuery(pageable));
    }

    @GetMapping("/tokens/directors")
    @Secured({"ADMIN"})
    public Page<TokenDTO> getDirectorTokens(Pageable pageable) {
        return userQueryHandler.handle(new GetDirectorTokensQuery(pageable));
    }

    @GetMapping("/unassigned")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Page<User> getUnassignedStudents(Pageable pageable) {
        return userQueryHandler.handle(new GetUnassignedStudentsQuery(pageable));
    }
}
