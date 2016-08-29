package gaddam1987.github.mservices.search.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RefreshScope
@CrossOrigin
@RestController
@RequestMapping("/ping")
public class HelloController {

    @Value("${shops.shutdown}")
    private String someDummyValue;

    @RequestMapping(method = GET)
    public ResponseEntity<?> get() {
        return ok(someDummyValue);
    }
}
