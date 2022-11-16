package de.thi.inf.sesa.hexa.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor

public class Post {
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String title;
    private String content;
    private LocalDateTime date;
    private UserReference userRef;

    public Post() {
        this.id = UUID.randomUUID();
    }
}
