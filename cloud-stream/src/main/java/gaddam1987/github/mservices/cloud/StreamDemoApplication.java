package gaddam1987.github.mservices.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.cloud.stream.messaging.Source.OUTPUT;

@SpringBootApplication
@EnableBinding(Source.class)
public class StreamDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(StreamDemoApplication.class, args);
    }

    @Bean
    @InboundChannelAdapter(value = OUTPUT)
    public MessageSource<String> timerMessageSource() {
        return () -> {
            GenericMessage<String> stringGenericMessage = new GenericMessage<>("Helooo " + new SimpleDateFormat().format(new Date()));
            System.out.println(stringGenericMessage);
            return stringGenericMessage;
        };
    }
}
