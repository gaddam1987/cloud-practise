package gaddam1987.github.mservices.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableConfigurationProperties(value = AzureConfigurationProperties.class)
public class AzureEventHubConsumer {

    public static void main(String[] args) {
        run(AzureEventHubConsumer.class);
    }

}
