package gaddam1987.github.mservices.azure.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;

import static java.lang.System.getProperty;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class DummyRequestSender {

    @Autowired
    EventSender eventSender;

    @Autowired
    RequestStore requestStore;

    @RequestMapping(method = RequestMethod.GET, path = "/ping")
    public ResponseEntity<String> pong() {
        String correlationId = UUID.randomUUID().toString();
        PingEvent pingEvent = new PingEvent();
        pingEvent.setMessage(getProperty("application.instance.id") + ":" + correlationId);
        pingEvent.setCorrelationId(correlationId);

        requestStore.add(correlationId, pingEvent);

        eventSender.send(pingEvent);

        return new ResponseEntity<String>("Send", OK);
    }
}
