package de.thi.inf.sesa.hexa.adapter.jpa;

import de.thi.inf.sesa.hexa.adapter.jpa.entities.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface JpaPostRepository extends CrudRepository<PostEntity, String> {
}
