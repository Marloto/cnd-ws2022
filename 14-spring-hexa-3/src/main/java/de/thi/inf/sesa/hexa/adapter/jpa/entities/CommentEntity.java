package de.thi.inf.sesa.hexa.adapter.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CommentEntity {
    @Id
    private String id;
    private String content;
    private LocalDateTime date;
    private String userRef;
    private String postRef;
}
