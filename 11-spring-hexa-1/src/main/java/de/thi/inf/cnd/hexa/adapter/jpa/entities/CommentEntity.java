package de.thi.inf.cnd.hexa.adapter.jpa.entities;

import lombok.AccessLevel;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class CommentEntity {
    @Id
    private UUID id;
    private String text;
    private LocalDateTime date;
    private String userRef;
}
