package de.thi.inf.sesa.rest.repository;

import de.thi.inf.sesa.rest.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PostRepository extends CrudRepository<Post, UUID> {
}
