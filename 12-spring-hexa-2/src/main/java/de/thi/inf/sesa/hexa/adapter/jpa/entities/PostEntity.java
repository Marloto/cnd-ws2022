package de.thi.inf.sesa.hexa.adapter.jpa.entities;

import de.thi.inf.sesa.hexa.domain.model.Post;
import de.thi.inf.sesa.hexa.domain.model.UserReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
public class PostEntity {
    @Id
    private String id;
    private String title;
    private String content;
    private LocalDateTime date;
    private String userRef;

    public PostEntity() {
    }

    public Post toPost() {
        return new Post(
                UUID.fromString(this.getId()),
                this.getTitle(),
                this.getContent(),
                this.getDate(),
                this.getUserRef() != null ? new UserReference(UUID.fromString(this.getUserRef())) : null);
    }
}
