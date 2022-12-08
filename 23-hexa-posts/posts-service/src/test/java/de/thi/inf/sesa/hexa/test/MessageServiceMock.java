package de.thi.inf.sesa.hexa.test;

import de.thi.inf.sesa.hexa.adapter.mqtt.MessagingService;
import de.thi.inf.sesa.hexa.domain.model.Post;
import de.thi.inf.sesa.hexa.ports.outgoing.PostEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class MessageServiceMock {

    Logger logger = LoggerFactory.getLogger(MessageServiceMock.class);

    @Bean
    @Primary
    public MessagingService nameService() {
        return new MessagingService() {
            @Override
            public void publish(int qos, Object object) {
                System.out.println("Test");
            }
        };
    }
}
