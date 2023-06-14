package pl.PFE.mySchool.domain.adapter.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.PFE.mySchool.application.command.post.ArchivePostCommand;
import pl.PFE.mySchool.application.command.post.CreatePostCommand;
import pl.PFE.mySchool.application.command.post.UpdatePostCommand;
import pl.PFE.mySchool.application.handler.post.PostCommandHandler;
import pl.PFE.mySchool.domain.service.post.PostCommandService;

@RequiredArgsConstructor
@Component
public class PostCommandHandlerImpl implements PostCommandHandler {

    private final PostCommandService postCommandService;

    @Override
    public void handle(CreatePostCommand command) {
        postCommandService.create(command);
    }

    @Override
    public void handle(UpdatePostCommand command) {
        postCommandService.update(command);
    }

    @Override
    public void handle(ArchivePostCommand command) {
        postCommandService.archiveById(command);
    }

}
