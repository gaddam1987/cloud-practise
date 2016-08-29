package gaddam1987.github.mservices.search.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@ComponentScan("gaddam1987.github.mservices.search")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
