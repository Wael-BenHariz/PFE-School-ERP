package pl.PFE.mySchool.domain.adapter.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.handler.post.PostQueryHandler;
import pl.PFE.mySchool.application.query.post.GetActivePostsByRealisationQuery;
import pl.PFE.mySchool.application.query.post.GetArchivedPostsByRealisationQuery;
import pl.PFE.mySchool.application.query.post.GetPostByIdQuery;
import pl.PFE.mySchool.application.query.post.GetRecentActivePosts;
import pl.PFE.mySchool.domain.model.Post;
import pl.PFE.mySchool.domain.service.post.PostQueryService;
import pl.PFE.mySchool.application.dto.PostDTO;

@RequiredArgsConstructor
@Component
public class PostQueryHandlerImpl implements PostQueryHandler {

    private final PostQueryService postQueryService;

    @Override
    public Page<PostDTO> handle(GetActivePostsByRealisationQuery query) {
        return postQueryService.getAllActiveByRealisation(query.realisationId(), query.pageable());
    }

    @Override
    public Page<Post> handle(GetArchivedPostsByRealisationQuery query) {
        return postQueryService.getAllArchivedByRealisation(query.realisationId(), query.pageable());
    }

    @Override
    public Post handle(GetPostByIdQuery query) {
        return postQueryService.getById(query.id());
    }

    @Override
    public Page<PostDTO> handle(GetRecentActivePosts query) {
        return postQueryService.getRecentActivePosts(query.pageable());
    }
}
