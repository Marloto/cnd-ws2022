package de.thi.inf.sesa.hexa.ports.outgoing;

import de.thi.inf.sesa.hexa.domain.model.Comment;
import de.thi.inf.sesa.hexa.domain.model.Post;

import java.util.List;

public interface CommentRepository {
    void save(Comment comment);
    List<Comment> listAllForPost(Post post);
}
