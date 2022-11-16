package de.thi.inf.sesa.hexa.adapter.mqtt;

import de.thi.inf.sesa.hexa.adapter.mqtt.dto.NewPostCreatedEvent;
import de.thi.inf.sesa.hexa.domain.model.Post;
import de.thi.inf.sesa.hexa.domain.model.UserReference;
import de.thi.inf.sesa.hexa.ports.outgoing.PostEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostEventsImpl implements PostEvents {
    @Autowired
    private MessagingService messagingService;

    @Override
    public void postCreated(Post post) {
        UserReference userRef = post.getUserRef();
        UUID reference = null;
        if(post.getUserRef() != null) {
            reference = post.getUserRef().getReference();
        }
        messagingService.publish(new NewPostCreatedEvent(post.getId(), post.getTitle(), reference));
    }
}
