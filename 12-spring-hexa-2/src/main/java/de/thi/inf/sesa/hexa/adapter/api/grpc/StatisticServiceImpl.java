package de.thi.inf.sesa.hexa.adapter.api.grpc;

import de.thi.inf.sesa.hexa.domain.PostService;
import de.thi.inf.sesa.hexa.statistic.PostsStatisticRequest;
import de.thi.inf.sesa.hexa.statistic.PostsStatisticResponse;
import de.thi.inf.sesa.hexa.statistic.StatisticServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class StatisticServiceImpl extends StatisticServiceGrpc.StatisticServiceImplBase {
    @Autowired
    private PostService posts;

    @Override
    public void posts(PostsStatisticRequest request, StreamObserver<PostsStatisticResponse> responseObserver) {
        long count = posts.countPosts();
        PostsStatisticResponse response = PostsStatisticResponse.newBuilder().setCount((int) count).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
