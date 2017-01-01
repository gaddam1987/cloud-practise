package gaddam1987.github.mservices.consumer;

import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.eventhubs.PartitionReceiver;
import com.microsoft.azure.servicebus.ConnectionStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.time.Instant;
import java.util.function.Consumer;

import static java.nio.charset.Charset.defaultCharset;

//@Component
public class ReceiveByDateTime implements CommandLineRunner {

    @Autowired
    AzureConfigurationProperties configurationProperties;

    @Override
    public void run(String... args) throws Exception {
        ConnectionStringBuilder connStr = new ConnectionStringBuilder(configurationProperties.getNamespace(),
                configurationProperties.getEventHubName(),
                configurationProperties.getSasKeyName(),
                configurationProperties.getSasKey());

        EventHubClient eventHubClient = EventHubClient.createFromConnectionString(connStr.toString()).get();

        String partitionId = "1"; // API to get PartitionIds will be released as part of V0.2

        PartitionReceiver receiver = eventHubClient.createEpochReceiver(
                EventHubClient.DEFAULT_CONSUMER_GROUP_NAME,
                partitionId,
                Instant.now(),
                1).get();

        System.out.println("date-time receiver created...");

        try {
            while (true) {
                receiver.receive(100).thenAccept(new Consumer<Iterable<EventData>>() {
                    public void accept(Iterable<EventData> receivedEvents) {
                        int batchSize = 0;
                        if (receivedEvents != null) {
                            for (EventData receivedEvent : receivedEvents) {
                                System.out.print(String.format("Offset: %s, SeqNo: %s, EnqueueTime: %s",
                                        receivedEvent.getSystemProperties().getOffset(),
                                        receivedEvent.getSystemProperties().getSequenceNumber(),
                                        receivedEvent.getSystemProperties().getEnqueuedTime()));
                                System.out.println(String.format("| Message Payload from Time based: %s", new String(receivedEvent.getBody(), defaultCharset())));
                                batchSize++;
                            }
                        }

                        System.out.println(String.format("ReceivedBatch Size: %s", batchSize));
                    }
                }).get();
            }
        } finally {
            // this is paramount; max number of concurrent receiver per consumergroup per partition is 5
            receiver.close().get();
        }
    }
}
