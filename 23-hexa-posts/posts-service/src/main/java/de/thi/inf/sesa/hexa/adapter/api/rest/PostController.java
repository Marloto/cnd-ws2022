package de.thi.inf.sesa.hexa.adapter.api.rest;

import de.thi.inf.sesa.hexa.adapter.api.rest.dto.CreatePostRequest;
import de.thi.inf.sesa.hexa.adapter.api.rest.dto.PostResponse;
import de.thi.inf.sesa.hexa.domain.PostService;
import de.thi.inf.sesa.hexa.domain.model.UserReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import de.thi.inf.sesa.hexa.domain.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService service;

    @PostMapping
    public PostResponse createPost(@RequestBody CreatePostRequest request) {
        Post p = service.createPost(request.getTitle(), request.getContent(), new UserReference(UUID.randomUUID())); // currently random user
        return new PostResponse(p.getId(), p.getTitle(), p.getContent(), p.getDate());
    }

    @GetMapping
    public List<PostResponse> listNewestPost() {
        List<Post> list = service.listNewestPost();
        List<PostResponse> posts = new ArrayList<>();
        for(Post p : list) {
            posts.add(new PostResponse(p.getId(), p.getTitle(), p.getContent(), p.getDate()));
        }
        return posts;
    }

    @GetMapping("/{id}")
    public PostResponse findPost(@PathVariable String id) {
        Post p = service.findPost(UUID.fromString(id));
        return new PostResponse(p.getId(), p.getTitle(), p.getContent(), p.getDate());
    }
}
