package pl.PFE.mySchool.application.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.PFE.mySchool.domain.model.Post;
import pl.PFE.mySchool.domain.shared.AccessGuard;
import pl.PFE.mySchool.application.dto.PostDTO;
import pl.PFE.mySchool.application.handler.post.PostQueryHandler;
import pl.PFE.mySchool.application.query.post.GetPostByIdQuery;
import pl.PFE.mySchool.application.query.post.GetRecentActivePosts;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostQueryController {

    private final PostQueryHandler postQueryHandler;
    private final AccessGuard accessGuard;

    @GetMapping("/{id}")
    @Secured({"TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public Post getPostById(@PathVariable("id") Long id) {
        accessGuard.checkAccessToPost(id);
        return postQueryHandler.handle(new GetPostByIdQuery(id));
    }

    @GetMapping("/recent")
    @Secured({"STUDENT"})
    public Page<PostDTO> getRecentPosts(Pageable pageable) {
        return postQueryHandler.handle(new GetRecentActivePosts(pageable));
    }
}
