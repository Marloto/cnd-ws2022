package de.thi.inf.cnd.rest.controller;

import de.thi.inf.cnd.rest.model.Post;
import de.thi.inf.cnd.rest.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

// Versionierung
// -> Erweiterung des Pfads ... z.B. /api/v1/post
// -> Header, Version: v1
// -> Query, ?version=1

// Zustandsloses Protokoll?
// -> Alles mitsenden, was notwendig ist
// -> oder... eine möglichkeit die Sitzung wieder zu identifizieren

@RestController
@RequestMapping(path = "/post")
public class PostController {

    private final PostRepository repository;

    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public Iterable<Post> getAllPosts() {
        return repository.findAll();
        // ggf. sind hiermit Informationen verbunden die nicht
        // über die Anfrage herausgegeben werden dürfen
    }

    @GetMapping("/{id}")
    public Post getOnePost(@PathVariable  UUID id) {
        Optional<Post> post = repository.findById(id);
        if(post.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return post.get();
        // ggf. sind hiermit Informationen verbunden die nicht
        // über die Anfrage herausgegeben werden dürfen
    }

    @PostMapping("/")
    public ResponseEntity createPost(@RequestBody Post post) {
        // ID könnte vergeben sein,
        // bestimmte Informationen sind überflüssig
        // TODO: check post object
        repository.save(post);
        // TODO: Location
        return new ResponseEntity(HttpStatus.CREATED);
    }

    // Element suchen, ggf. nicht finden, felder aktualisieren
    // -> welcher Felder sind überhaupt aktualisierbar
    public Post updatePost(UUID id, Post post) {
        return null;
    }

    // Ggf. suchen, und wenn gefunden löschen
    public void deletePost(UUID id) {
        return;
    }
}
