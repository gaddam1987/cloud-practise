package gaddam1987.github.mservices.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SellerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SellerApplication.class, args);
    }

}
