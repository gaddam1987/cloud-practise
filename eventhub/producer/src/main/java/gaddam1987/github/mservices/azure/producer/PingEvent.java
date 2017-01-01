package gaddam1987.github.mservices.azure.producer;


import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;


@Getter
@Setter
public class PingEvent implements Event {
    private String message;
    private LocalDate localDate;
    private int eventNumber;
    private String correlationId;
}
