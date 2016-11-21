package gaddam1987.github.mservices.intg.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = RMessagingApplication.NOTIFICATIONS)
    void onNotification(Message<Notification> notificationMessage) {
        System.out.println("received " + notificationMessage.toString());
        System.out.println("payload " + notificationMessage.getPayload());
        System.out.println("Headers " + notificationMessage.getHeaders());
    }
}
