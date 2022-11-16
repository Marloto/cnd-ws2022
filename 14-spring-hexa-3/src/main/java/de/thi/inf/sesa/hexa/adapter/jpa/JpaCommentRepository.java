package de.thi.inf.sesa.hexa.adapter.jpa;

import de.thi.inf.sesa.hexa.adapter.jpa.entities.CommentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JpaCommentRepository extends CrudRepository<CommentEntity, String> {
    List<CommentEntity> findByPostRef(String postRef);
}
