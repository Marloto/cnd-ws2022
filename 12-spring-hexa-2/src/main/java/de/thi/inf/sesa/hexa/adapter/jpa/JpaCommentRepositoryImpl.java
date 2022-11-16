package de.thi.inf.sesa.hexa.adapter.jpa;

import de.thi.inf.sesa.hexa.adapter.jpa.entities.CommentEntity;
import de.thi.inf.sesa.hexa.domain.model.Comment;
import de.thi.inf.sesa.hexa.domain.model.Post;
import de.thi.inf.sesa.hexa.domain.model.UserReference;
import de.thi.inf.sesa.hexa.ports.outgoing.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class JpaCommentRepositoryImpl implements CommentRepository {

    @Autowired
    private JpaCommentRepository repo;

    @Override
    public void save(Comment comment) {
        CommentEntity entity = new CommentEntity();

        entity.setId(comment.getId().toString());
        entity.setContent(comment.getContent());
        entity.setPostRef(comment.getPost().getId().toString());
        entity.setDate(comment.getDate());
        if(comment.getUserRef() != null) {
            entity.setUserRef(comment.getUserRef().getReference().toString());
        }
        repo.save(entity);
    }

    @Override
    public List<Comment> listAllForPost(Post post) {
        List<CommentEntity> list = repo.findByPostRef(post.getId().toString());
        List<Comment> comments = new ArrayList<>();
        for (CommentEntity el : list) {
            comments.add(new Comment(
                    UUID.fromString(el.getId()),
                    el.getContent(),
                    el.getDate(),
                    post,
                    el.getUserRef() != null && el.getUserRef().length() > 0 ? new UserReference(UUID.fromString(el.getUserRef())) : null
            ));
        }
        return comments;
    }
}
