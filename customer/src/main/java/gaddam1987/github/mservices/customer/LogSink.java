package gaddam1987.github.mservices.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

@EnableBinding(Sink.class)
public class LogSink {
    private static Logger logger = LoggerFactory.getLogger(LogSink.class);

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void loggerSink(Object payload) {
        logger.info("Received: " + payload);
    }
}
