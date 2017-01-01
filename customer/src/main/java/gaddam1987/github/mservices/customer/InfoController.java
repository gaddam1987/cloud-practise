package gaddam1987.github.mservices.customer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/ping")
    public String hello() {
        return "POLO from server running on : " + serverPort;
    }
}
