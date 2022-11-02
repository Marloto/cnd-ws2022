package de.thi.inf.cnd.rest.controller;

import com.google.protobuf.Empty;
import de.thi.inf.cnd.grpc.CountCommentsRequest;
import de.thi.inf.cnd.grpc.CountCommentsResponse;
import de.thi.inf.cnd.grpc.ListPostIdsRequest;
import de.thi.inf.cnd.grpc.ListPostIdsResponse;
import de.thi.inf.cnd.grpc.StatisticServiceGrpc.StatisticServiceImplBase;
import de.thi.inf.cnd.rest.model.Comment;
import de.thi.inf.cnd.rest.model.Post;
import de.thi.inf.cnd.rest.repository.PostRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@GrpcService
public class StatisticController extends StatisticServiceImplBase {
    @Autowired
    private PostRepository postRepository;

    @Override
    public void countComments(CountCommentsRequest request, StreamObserver<CountCommentsResponse> responseObserver) {
        String postId = request.getPostId();
        int count = 0;
        if(postId != null && !postId.isBlank()) {
            UUID postUuid = UUID.fromString(postId);
            Optional<Post> post = this.postRepository.findById(postUuid);
            if(post.isPresent()) {
                List<Comment> comments = post.get().getComments();
                count = comments.size();
            }
        }
        CountCommentsResponse response = CountCommentsResponse.newBuilder().setCount(count).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void listPostIds(ListPostIdsRequest request, StreamObserver<ListPostIdsResponse> responseObserver) {
        Iterable<Post> all = this.postRepository.findAll();
        ListPostIdsResponse.Builder builder = ListPostIdsResponse.newBuilder();
        for(Post p : all) {
            builder.addPostId(p.getId().toString());
        }
        ListPostIdsResponse response = builder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}