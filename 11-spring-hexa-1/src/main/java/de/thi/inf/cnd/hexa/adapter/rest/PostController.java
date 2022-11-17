package de.thi.inf.cnd.hexa.adapter.rest;

import de.thi.inf.cnd.hexa.adapter.rest.dto.CreatePostRequest;
import de.thi.inf.cnd.hexa.adapter.rest.dto.PostResponse;
import de.thi.inf.cnd.hexa.domain.PostService;
import de.thi.inf.cnd.hexa.domain.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService service;

//    @GetMapping
//    public Iterable<Post> getAllPosts() {
//        return service.listPosts();
//    }

//    @GetMapping("/{id}")
//    public Post getOnePost(@PathVariable UUID id) {
//        Optional<Post> post = service.loadPost(id);
//        if(post.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        return post.get();
//    }

    @PostMapping
    public PostResponse createPost(@RequestBody CreatePostRequest post) {
        Post save = service.createNewPost(post.getTitle(), post.getContent());
        PostResponse resp = new PostResponse();
        resp.setId(save.getId());
        resp.setContent(save.getContent());
        resp.setTitle(save.getTitle());
        return resp;
    }

//    @PutMapping("/{id}")
//    public Post updatePost(@PathVariable UUID id, @RequestBody Post post) {
//        Optional<Post> oldPost = repository.findById(id);
//        if(oldPost.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        return repository.save(oldPost.map(p -> {
//            p.setContent(post.getContent());
//            p.setTitle(post.getTitle());
//            return p;
//        }).get());
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity deletePost(@PathVariable UUID id) {
//        repository.deleteById(id);
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }
}