package gaddam1987.github.mservices.azure.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.UUID;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableConfigurationProperties(value = AzureConfigurationProperties.class)
@Slf4j
public class AzureEventHubProducer {
    public static void main(String[] args) {
        String s = UUID.randomUUID().toString();
        String applicationId = String.format("Producer:%s", s);
        System.setProperty("application.instance.id", applicationId);
        log.info("Starting application &s", applicationId);
        run(AzureEventHubProducer.class);
    }
}
