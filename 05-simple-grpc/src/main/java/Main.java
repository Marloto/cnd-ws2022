import com.google.protobuf.InvalidProtocolBufferException;
import de.thi.inf.sesa.simplegrpc.HelloResponse;

public class Main {
    public static void main(String[] args) {
        HelloResponse response = HelloResponse.newBuilder()
            .setGreeting("Hello, World!")
            .build();
        byte[] message = response.toByteArray();

        try {
            HelloResponse helloResponse = HelloResponse.parseFrom(message);
            System.out.println(helloResponse.getGreeting());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}