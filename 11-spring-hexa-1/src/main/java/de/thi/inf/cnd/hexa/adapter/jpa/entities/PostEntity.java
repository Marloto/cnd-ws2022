package de.thi.inf.cnd.hexa.adapter.jpa.entities;

import lombok.AccessLevel;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class PostEntity {
    @Id
    private UUID id;
    private String title;
    private String content;
    private LocalDateTime date;
    private String userRef;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CommentEntity> comments;
}
