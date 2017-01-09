package gaddam1987.github.mservices.azure.producer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.eventhubs.PartitionSender;
import com.microsoft.azure.servicebus.ConnectionStringBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;


@Component
@Slf4j
public class EventSender {
    public final AtomicInteger a = new AtomicInteger(0);

    @Autowired
    AzureConfigurationProperties configurationProperties;

    public void send(PingEvent pingEvent) {
        pingEvent.setEventNumber(a.getAndIncrement());

        ConnectionStringBuilder connStr = new ConnectionStringBuilder(configurationProperties.getNamespace(),
                configurationProperties.getPaymentEventHubName(),
                configurationProperties.getSasKeyName(),
                configurationProperties.getSasKey());

        try {
            EventHubClient ehClient = EventHubClient.createFromConnectionString(connStr.toString()).get();
            Gson gson = new GsonBuilder().create();
            byte[] payloadBytes = gson.toJson(pingEvent).getBytes(Charset.defaultCharset());
            EventData sendEvent = new EventData(payloadBytes);
            ehClient.send(sendEvent);
        } catch (Exception e) {
            log.warn("Some shit happened", e);
        }
        log.info("{}: Send Complete... for {}", Instant.now(), pingEvent);
    }
}
