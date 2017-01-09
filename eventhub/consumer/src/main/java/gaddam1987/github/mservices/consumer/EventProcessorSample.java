package gaddam1987.github.mservices.consumer;

import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.eventhubs.PartitionReceiver;
import com.microsoft.azure.eventprocessorhost.EventProcessorHost;
import com.microsoft.azure.eventprocessorhost.EventProcessorOptions;
import com.microsoft.azure.servicebus.ConnectionStringBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.logging.Level;

import static com.microsoft.azure.eventhubs.EventHubClient.DEFAULT_CONSUMER_GROUP_NAME;

@Component
public class EventProcessorSample implements CommandLineRunner {

    @Autowired
    AzureConfigurationProperties configurationProperties;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("sample.............");
        ConnectionStringBuilder connStr = new ConnectionStringBuilder(configurationProperties.getNamespace(),
                configurationProperties.getPaymentEventHubName(),
                configurationProperties.getSasKeyName(),
                configurationProperties.getSasKey());

        configurationProperties.setHostName(UUID.randomUUID().toString());

        EventProcessorHost host = new EventProcessorHost(configurationProperties.getHostName(), //Host Name
                configurationProperties.getPaymentEventHubName(), // eventHubPath
                configurationProperties.getPaymentConsumerGroup1(), //ConsumrGroupName
                connStr.toString(), //eventHubConnectionString
                configurationProperties.getStorageConnectionString(), //storageConnectionString
                configurationProperties.getStorageAccountName()); //storageAccountName


        System.out.println("Registering host named " + host.getHostName());

        EventProcessorOptions options = new EventProcessorOptions();

        options.setExceptionNotification(new ErrorNotificationHandler());
        options.setInitialOffsetProvider(s -> {
            return "0";
        });

        try {
            host.registerEventProcessor(EventProcessor.class, options).get();

        } catch (Exception e) {
            System.out.print("Failure while registering: ");
            if (e instanceof ExecutionException) {
                Throwable inner = e.getCause();
                System.out.println(inner.toString());
            } else {
                System.out.println(e.toString());
            }
        }
    }

}
