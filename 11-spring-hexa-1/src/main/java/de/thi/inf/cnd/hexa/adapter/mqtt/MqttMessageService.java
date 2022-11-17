package de.thi.inf.cnd.hexa.adapter.mqtt;

import de.thi.inf.cnd.hexa.adapter.mqtt.dto.NewPostEvent;
import de.thi.inf.cnd.hexa.domain.model.Post;
import de.thi.inf.cnd.hexa.ports.out.PostEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttMessageService implements PostEvents {

    @Autowired
    private PublisherService service;

    @Override
    public void publishNewPost(Post post) {
        NewPostEvent event = new NewPostEvent();
        event.setId(post.getId().toString());
        event.setTitle(post.getTitle());
        event.setContent(post.getContent());
        service.publish(post);
    }
}
