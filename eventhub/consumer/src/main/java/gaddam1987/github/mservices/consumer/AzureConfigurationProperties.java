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

    public String getPaymentEventHubName() {
        return paymentEventHubName;
    }

    public void setPaymentEventHubName(String paymentEventHubName) {
        this.paymentEventHubName = paymentEventHubName;
    }

    public String getPaymentConsumerGroup1() {
        return paymentConsumerGroup1;
    }

    public void setPaymentConsumerGroup1(String paymentConsumerGroup1) {
        this.paymentConsumerGroup1 = paymentConsumerGroup1;
    }

    public String getPaymentConsumerGroup2() {
        return paymentConsumerGroup2;
    }

    public void setPaymentConsumerGroup2(String paymentConsumerGroup2) {
        this.paymentConsumerGroup2 = paymentConsumerGroup2;
    }

    private String paymentEventHubName;
    private String paymentConsumerGroup1;
    private String paymentConsumerGroup2;

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
