package gaddam1987.github.mservices.azure.producer;

import java.util.Random;
import java.util.UUID;

public class PayloadEvent {
    PayloadEvent(final int seed) {
        this.id = "telemetryEvent1-critical-eventid-2345" + seed;
        this.strProperty = UUID.randomUUID().toString();
        this.longProperty = seed * new Random().nextInt(seed);
        this.intProperty = seed * new Random().nextInt(seed);
    }

    public final String id;
    public final String strProperty;
    public final long longProperty;
    public final int intProperty;


    @Override
    public String toString() {
        return "PayloadEvent{" +
                "id='" + id + '\'' +
                ", strProperty='" + strProperty + '\'' +
                ", longProperty=" + longProperty +
                ", intProperty=" + intProperty +
                '}';
    }
}
