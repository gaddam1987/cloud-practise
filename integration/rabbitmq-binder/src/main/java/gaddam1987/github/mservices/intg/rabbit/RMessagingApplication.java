package gaddam1987.github.mservices.intg.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
public class RMessagingApplication {
    public static final String NOTIFICATIONS = "notifications";

    @Bean
    public InitializingBean prepareQueues(AmqpAdmin amqpAdmin) {
        return () -> {
            Queue queue = new Queue(NOTIFICATIONS, true);
            DirectExchange exchange = new DirectExchange(NOTIFICATIONS);
            Binding binding = BindingBuilder.bind(queue).to(exchange).with(NOTIFICATIONS);
            amqpAdmin.declareQueue(queue);
            amqpAdmin.declareExchange(exchange);
            amqpAdmin.declareBinding(binding);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(RMessagingApplication.class, args);
    }
}
