package tn.esprit.eurekad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaDApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaDApplication.class, args);
    }

}
