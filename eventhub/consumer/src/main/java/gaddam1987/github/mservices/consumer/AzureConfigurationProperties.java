package gaddam1987.github.mservices.consumer;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "azure", locations = "file:///C:/azurecreds/azurecreds.yaml")
/**
 * Todo load properties from vault
 */
public class AzureConfigurationProperties {

    private String STORAGE_CONNECTION_STRING_FORMAT = "DefaultEndpointsProtocol=https;AccountName=%s;AccountKey=%s";
    private String consumerGroupName;
    private String namespace;
    private String eventHubName;
    private String sasKeyName;
    private String sasKey;
    private String storageAccountName;
    private String storageAccountKey;
    private String hostName;

    public String getStorageConnectionString() {
        return String.format(STORAGE_CONNECTION_STRING_FORMAT, storageAccountName, storageAccountKey);
    }

    public String getStorageAccountName() {
        return storageAccountName;
    }

    public String getConsumerGroupName() {
        return consumerGroupName;
    }

    public void setConsumerGroupName(String consumerGroupName) {
        this.consumerGroupName = consumerGroupName;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getEventHubName() {
        return eventHubName;
    }

    public void setEventHubName(String eventHubName) {
        this.eventHubName = eventHubName;
    }

    public String getSasKeyName() {
        return sasKeyName;
    }

    public void setSasKeyName(String sasKeyName) {
        this.sasKeyName = sasKeyName;
    }

    public String getSasKey() {
        return sasKey;
    }

    public void setSasKey(String sasKey) {
        this.sasKey = sasKey;
    }

    public void setStorageAccountName(String storageAccountName) {
        this.storageAccountName = storageAccountName;
    }

    public void setStorageAccountKey(String storageAccountKey) {
        this.storageAccountKey = storageAccountKey;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}
