package gaddam1987.github.mservices.azure.producer;

import com.microsoft.azure.servicebus.ConnectionStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    @Autowired
    AzureConfigurationProperties configurationProperties;

    public void listener() {
        ConnectionStringBuilder connStr = new ConnectionStringBuilder(configurationProperties.getNamespace(),
                configurationProperties.getEventHubName(),
                configurationProperties.getSasKeyName(),
                configurationProperties.getSasKey());
    }
}
