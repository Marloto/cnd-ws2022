import de.thi.inf.sesa.hexa.statistic.PostsStatisticRequest;
import de.thi.inf.sesa.hexa.statistic.PostsStatisticResponse;
import de.thi.inf.sesa.hexa.statistic.StatisticServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcTestClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9898).usePlaintext().build();
        StatisticServiceGrpc.StatisticServiceBlockingStub stub = StatisticServiceGrpc.newBlockingStub(channel);

        PostsStatisticRequest request = PostsStatisticRequest.newBuilder().build();

        PostsStatisticResponse response = stub.posts(request);
        System.out.println(response.getCount());
    }
}
