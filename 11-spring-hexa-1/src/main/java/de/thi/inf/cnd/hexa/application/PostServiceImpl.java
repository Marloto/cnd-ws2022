package de.thi.inf.cnd.hexa.application;

import de.thi.inf.cnd.hexa.domain.PostService;
import de.thi.inf.cnd.hexa.ports.out.MessagePort;
import de.thi.inf.cnd.hexa.ports.out.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private MessagePort messages;

    @Override
    public Post createNewPost(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        repository.save(post);
        messages.publishNewPost(post);
        return post;
    }
}
