package de.thi.inf.sesa.hexa.domain;

import de.thi.inf.sesa.hexa.domain.model.Comment;
import de.thi.inf.sesa.hexa.domain.model.Post;
import de.thi.inf.sesa.hexa.domain.model.UserReference;

import java.util.List;
import java.util.UUID;

public interface PostService {
    Post createPost(String title, String content, UserReference userRef);
    boolean hidePost(UUID id);
    Post findPost(UUID id);
    List<Post> listNewestPost();
    long countPosts();
    List<Comment> listCommentsForPost(UUID postRef);
    Comment createCommentForPost(UUID postRef, String content);
}
