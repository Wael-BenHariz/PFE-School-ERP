package pl.PFE.mySchool.application.handler.post;

import org.springframework.data.domain.Page;
import pl.PFE.mySchool.domain.model.Post;
import pl.PFE.mySchool.application.dto.PostDTO;
import pl.PFE.mySchool.application.query.post.GetActivePostsByRealisationQuery;
import pl.PFE.mySchool.application.query.post.GetArchivedPostsByRealisationQuery;
import pl.PFE.mySchool.application.query.post.GetPostByIdQuery;
import pl.PFE.mySchool.application.query.post.GetRecentActivePosts;

public interface PostQueryHandler {
    Page<PostDTO> handle(GetActivePostsByRealisationQuery query);

    Page<Post> handle(GetArchivedPostsByRealisationQuery query);

    Post handle(GetPostByIdQuery query);

    Page<PostDTO> handle(GetRecentActivePosts query);
}
