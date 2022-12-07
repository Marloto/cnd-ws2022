package de.thi.inf.sesa.rest.controller;


import de.thi.inf.sesa.rest.model.Comment;
import de.thi.inf.sesa.rest.model.Post;
import de.thi.inf.sesa.rest.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    @Autowired
    private PostRepository repository;

    @GetMapping
    public Iterable<Comment> getAll(@PathVariable UUID postId) {
        Optional<Post> post = repository.findById(postId);
        if(post.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Post post1 = post.get();
        return post1.getComments();
    }

    @PostMapping
    public Comment createComment(@PathVariable UUID postId, @RequestBody Comment comment) {
        Optional<Post> post = repository.findById(postId);
        if(post.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Post p = post.get();
        p.getComments().add(comment);
        repository.save(p);
        return comment;
    }
}
