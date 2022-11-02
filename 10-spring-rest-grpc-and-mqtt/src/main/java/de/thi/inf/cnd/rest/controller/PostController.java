package de.thi.inf.cnd.rest.controller;

import de.thi.inf.cnd.rest.model.Post;
import de.thi.inf.cnd.rest.repository.PostRepository;
import de.thi.inf.cnd.rest.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;



@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostRepository repository;

    @Autowired
    private PublisherService service;

    @GetMapping
    public Iterable<Post> getAllPosts() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Post getOnePost(@PathVariable UUID id) {
        Optional<Post> post = repository.findById(id);
        if(post.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return post.get();
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        Post save = repository.save(post);
        service.publishNewPost(save);
        return save;
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable UUID id, @RequestBody Post post) {
        Optional<Post> oldPost = repository.findById(id);
        if(oldPost.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return repository.save(oldPost.map(p -> {
            p.setContent(post.getContent());
            p.setTitle(post.getTitle());
            return p;
        }).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable UUID id) {
        repository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}