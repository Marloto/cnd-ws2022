package de.thi.inf.sesa.hexa.application;

import de.thi.inf.sesa.hexa.domain.model.Comment;
import de.thi.inf.sesa.hexa.domain.model.Post;
import de.thi.inf.sesa.hexa.domain.PostService;
import de.thi.inf.sesa.hexa.domain.model.UserReference;
import de.thi.inf.sesa.hexa.ports.outgoing.CommentRepository;
import de.thi.inf.sesa.hexa.ports.outgoing.PostEvents;
import de.thi.inf.sesa.hexa.ports.outgoing.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository posts;

    @Autowired
    private CommentRepository comments;

    @Autowired
    private PostEvents events;

    @Override
    public Post createPost(String title, String content, UserReference userRef) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setUserRef(userRef);

        posts.save(post);
        events.postCreated(post);
        return post;
    }

    @Override
    public boolean hidePost(UUID id) {
        return false;
    }

    @Override
    public Post findPost(UUID id) {
        return posts.findById(id);
    }

    @Override
    public List<Post> listNewestPost() {
        return posts.listAll();
    }

    @Override
    public long countPosts() {
        return posts.count();
    }

    @Override
    public List<Comment> listCommentsForPost(UUID postRef) {
        Post post = posts.findById(postRef);
        return comments.listAllForPost(post);
    }

    @Override
    public Comment createCommentForPost(UUID postRef, String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        Post post = posts.findById(postRef);
        comment.setPost(post);
        comments.save(comment);
        return comment;
    }
}
