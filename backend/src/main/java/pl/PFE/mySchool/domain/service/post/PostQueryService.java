package pl.PFE.mySchool.domain.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.PFE.mySchool.domain.exception.post.PostNotFoundException;
import pl.PFE.mySchool.domain.model.Post;
import pl.PFE.mySchool.domain.model.User;
import pl.PFE.mySchool.infrastructure.repository.PostRepository;
import pl.PFE.mySchool.application.dto.PostDTO;

@Service
@RequiredArgsConstructor
public class PostQueryService {

    private final PostRepository postRepository;

    public Page<PostDTO> getAllActiveByRealisation(Long realisationId, Pageable pageable) {
        Page<Post> posts = postRepository.findByRealisationIdAndArchived(realisationId, false, pageable);
        return posts.map((PostDTO::new));
    }


    public Page<Post> getAllArchivedByRealisation(Long realisationId, Pageable pageable) {
        return postRepository.findByRealisationIdAndArchived(realisationId, true, pageable);
    }


    public Post getById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
    }

    public Page<PostDTO> getRecentActivePosts(Pageable pageable) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return postRepository.findByRealisation_SchoolClass_Students_IdAndArchived(user.getId(), false, pageable)
                .map(PostDTO::new);
    }
}
