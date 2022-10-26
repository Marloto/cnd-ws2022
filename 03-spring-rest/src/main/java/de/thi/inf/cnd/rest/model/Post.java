package de.thi.inf.cnd.rest.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Post {
    private UUID id;
    private String title;
    private String content;
    private LocalDateTime date;
    private String userRef;

    private List<Comment> comments;

    public Post() {
        this.id = UUID.randomUUID();
        this.comments = new ArrayList<>();
    }
}
