package de.thi.inf.cnd.hexa.ports.out;

import de.thi.inf.cnd.hexa.domain.model.Post;

public interface PostRepository {
    public void save(Post post);
}
