package de.thi.inf.sesa.rest.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
public class Comment {
    @Id
    @Setter(AccessLevel.NONE)
    private UUID id; //guid ohne Setter
    private String text;
    private LocalDateTime date;
    private String userRef;

    public Comment() {
        this.id = UUID.randomUUID();
    }
}
