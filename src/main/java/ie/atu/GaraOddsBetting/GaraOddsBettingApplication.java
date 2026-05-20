package ie.atu.GaraOddsBetting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication // marks this as a spring boot application
@EnableFeignClients
public class GaraOddsBettingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaraOddsBettingApplication.class, args);
        // http://localhost:8081/swagger-ui/index.html
    }
}