package gaddam1987.github.mservices.cache;

import static java.lang.String.format;

public class SimpleRedisQueue {
    private String QUEUE_FORMAT = "queue:%s";
    private String queueName;
    private String queueKey;

    public SimpleRedisQueue(String queueName) {
        this.queueName = queueName;
        this.queueKey = format(QUEUE_FORMAT, queueName);
    }
}
