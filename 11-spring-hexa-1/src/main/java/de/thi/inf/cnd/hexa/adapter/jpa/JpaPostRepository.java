package de.thi.inf.cnd.hexa.adapter.jpa;

import de.thi.inf.cnd.hexa.adapter.jpa.entities.PostEntity;
import de.thi.inf.cnd.hexa.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JpaPostRepository extends CrudRepository<PostEntity, UUID> {
}
