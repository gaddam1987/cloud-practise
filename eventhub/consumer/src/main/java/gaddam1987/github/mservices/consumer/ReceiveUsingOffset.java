package gaddam1987.github.mservices.consumer;

import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.eventhubs.PartitionReceiver;
import com.microsoft.azure.servicebus.ConnectionStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

import static com.microsoft.azure.eventhubs.EventHubClient.DEFAULT_CONSUMER_GROUP_NAME;
import static com.microsoft.azure.eventhubs.PartitionReceiver.START_OF_STREAM;


public class ReceiveUsingOffset implements CommandLineRunner {

    @Autowired
    AzureConfigurationProperties configurationProperties;

    @Override
    public void run(String... args) throws Exception {
        ConnectionStringBuilder connStr = new ConnectionStringBuilder(configurationProperties.getNamespace(),
                configurationProperties.getEventHubName(),
                configurationProperties.getSasKeyName(),
                configurationProperties.getSasKey());
        EventHubClient eventHubClient = EventHubClient.createFromConnectionString(connStr.toString()).get();


        String partitionId = "0";
        PartitionReceiver partitionReceiver = eventHubClient.createEpochReceiver(DEFAULT_CONSUMER_GROUP_NAME, partitionId, START_OF_STREAM, false, 1).get();

        try {
            Iterable<EventData> receivedEvents = partitionReceiver.receive(100).get();

            while (true) {
                int batchSize = 0;
                if (receivedEvents != null) {
                    for (EventData receivedEvent : receivedEvents) {
                        System.out.println(String.format("Message Payload using offset: %s", new String(receivedEvent.getBody(), Charset.defaultCharset())));
                        System.out.println(String.format("Offset: %s, SeqNo: %s, EnqueueTime: %s",
                                receivedEvent.getSystemProperties().getOffset(),
                                receivedEvent.getSystemProperties().getSequenceNumber(),
                                receivedEvent.getSystemProperties().getEnqueuedTime()));
                        batchSize++;
                    }
                }
                System.out.println(String.format("ReceivedBatch Size: %s", batchSize));
                receivedEvents = partitionReceiver.receive(100).get();
            }
        } finally {
            // this is paramount; max number of concurrent receiver per consumergroup per partition is 5
            partitionReceiver.close().get();
        }
    }

}
