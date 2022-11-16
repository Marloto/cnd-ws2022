package de.thi.inf.sesa.hexa.adapter.api.rest;

import de.thi.inf.sesa.hexa.adapter.api.rest.dto.CommentResponse;
import de.thi.inf.sesa.hexa.adapter.api.rest.dto.CreateCommentRequest;
import de.thi.inf.sesa.hexa.domain.PostService;
import de.thi.inf.sesa.hexa.domain.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {
    @Autowired
    private PostService comments;
    @PostMapping
    public CommentResponse createCommentForPost(@PathVariable String postId, @RequestBody CreateCommentRequest request) {
        Comment c = comments.createCommentForPost(UUID.fromString(postId), request.getContent());
        return new CommentResponse(c.getId(), c.getContent(), c.getDate());
    }

    @GetMapping
    public List<CommentResponse> listCommentsForPost(@PathVariable String postId) {
        List<Comment> list = this.comments.listCommentsForPost(UUID.fromString(postId));
        List<CommentResponse> comments = new ArrayList<>();
        for(Comment c : list) {
            comments.add(new CommentResponse(c.getId(), c.getContent(), c.getDate()));
        }
        return comments;
    }
}
