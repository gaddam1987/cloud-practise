package gaddam1987.github.mservices.azure.producer;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RequestStore {
    private final Map<String, Object> concurrentHashMap = new ConcurrentHashMap<>();


    public void add(String correlationId, PingEvent pingEvent) {
        concurrentHashMap.put(correlationId, pingEvent);
    }

    public Optional<Object> get(String correlationId) {
        return Optional.of(concurrentHashMap.get(correlationId));
    }

}
