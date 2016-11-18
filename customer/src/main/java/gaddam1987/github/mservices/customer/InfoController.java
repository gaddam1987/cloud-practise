package gaddam1987.github.mservices.customer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @RequestMapping("/ping")
    public String hello() {
        return "POLO";
    }
}
