package gaddam1987.github.mservices.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@EnableBinding(Sink.class)
public class LogSink {
    private static Logger logger = LoggerFactory.getLogger(LogSink.class);

    //  @ServiceActivator(inputChannel = Sink.INPUT)
    // public void loggerSink(Object payload) {
    //   logger.info("Received: " + payload);
    //}
}
