package de.thi.inf.cnd.rest.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {
    private UUID id;
    private String text;
    private LocalDateTime date;
    private String userRef;

    public Comment() {
        this.id = UUID.randomUUID();
    }
}
