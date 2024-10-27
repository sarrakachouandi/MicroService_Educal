package com.esprit.microservices.foyer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FoyerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoyerApplication.class, args);
    }


}
