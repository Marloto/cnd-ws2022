package de.thi.inf.sesa.hexa.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Comment {
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String content;
    private LocalDateTime date;
    private Post post;
    private UserReference userRef;

    public Comment() {
        this.id = UUID.randomUUID();
    }
}
