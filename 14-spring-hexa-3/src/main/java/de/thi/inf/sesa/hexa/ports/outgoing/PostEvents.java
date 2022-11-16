package de.thi.inf.sesa.hexa.ports.outgoing;

import de.thi.inf.sesa.hexa.domain.model.Post;

public interface PostEvents {
    void postCreated(Post post);
}
