package de.thi.inf.cnd.hexa.domain;

import de.thi.inf.cnd.hexa.domain.model.Post;

public interface PostService {
    public Post createNewPost(String title, String content);
}
