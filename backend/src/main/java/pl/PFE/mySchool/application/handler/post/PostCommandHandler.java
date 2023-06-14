package pl.PFE.mySchool.application.handler.post;

import pl.PFE.mySchool.application.command.post.ArchivePostCommand;
import pl.PFE.mySchool.application.command.post.CreatePostCommand;
import pl.PFE.mySchool.application.command.post.UpdatePostCommand;

public interface PostCommandHandler {
    void handle(CreatePostCommand command);

    void handle(UpdatePostCommand command);

    void handle(ArchivePostCommand command);
}
