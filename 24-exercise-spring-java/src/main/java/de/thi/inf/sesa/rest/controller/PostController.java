package de.thi.inf.sesa.rest.controller;

import de.thi.inf.sesa.rest.model.Post;
import de.thi.inf.sesa.rest.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


// HTTP Anfragebestandteile?
// -> Header und Body (POST / PUT)
// -> HTTP-Methode im Header

// -> Content-Type, Accept [x]
// -> Custom-Header-Informationen -> Key: Value [x]
// -> Pfad [x] + ggf. Query

// Wie kann Bestandteile der Anfrage eine Versionierung der
// Schnittstelle erreicht werden?
// -> Strategie 1: Pfad-Prefix ... z.B. /api/v1/posts
// -> Strategie 2: Custom-Header und da Version
//                 abbilden, my-fancy-app-version: v1
// -> Strategie 3: Accept: application/custom.1+json
// -> Strategie 4: /posts?api=v1

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostRepository repository;

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
        return repository.save(post);
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
