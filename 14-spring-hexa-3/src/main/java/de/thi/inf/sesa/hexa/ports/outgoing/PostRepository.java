package de.thi.inf.sesa.hexa.ports.outgoing;

import de.thi.inf.sesa.hexa.domain.model.Comment;
import de.thi.inf.sesa.hexa.domain.model.Post;

import java.util.List;
import java.util.UUID;

public interface PostRepository {
    void save(Post post);
    List<Post> listAll();
    Post findById(UUID uuid);
    long count();
}
